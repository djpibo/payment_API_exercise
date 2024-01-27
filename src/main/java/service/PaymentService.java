package service;

import dto.*;
import entity.CardOrder;
import entity.Member;
import entity.UserCard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import repository.CardOrderRepository;
import repository.MemberRepository;
import repository.UserCardRepository;
import service.feign.PortoneClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PortoneClient portoneClient;
    private final EntityManager entityManager;
    private final CardOrderRepository cardOrderRepository;
    private final MemberRepository memberRepository;
    private final UserCardRepository userCardRepository;
    private final RedisService redisService;

    @Value("${portone.imp.key}")
    private String impKey;
    @Value("${portone.imp.secret}")
    private String impSecret;
    @Value("${portone.imp.merchant_uid}")
    private String merchantUid;

    // 회원정보조회
    public Member findUser(UserRequestDTO userRequestDTO) {
        return memberRepository.findUserById(userRequestDTO.getUserId());
    }

    /*
     - 보유 카드가 없으면 에러 응답
     - 보유 카드가 존재하면 포트원으로 결제 요청 & 거래 내역 적재
     - 포트원으로 결제 요청후 응답
    */
    public PortonePayResponseDTO pay(UserRequestDTO userRequestDTO) {
        UserCard userCard = cardOrderRepository.findCardByCardId(userRequestDTO.getCardId());
        Member member = cardOrderRepository.findUserByUserId(userRequestDTO.getUserId());

        PortonePayRequestDTO portonePayRequestDTO = PortonePayRequestDTO.builder()
                .cardNumber(userCard.getCardNumber())
                .expiry((userCard.getExpiry()))
                .amount(userRequestDTO.getAmount())
                .birth(member.getBirth())
                .merchantUid(merchantUid)
                .build();
        PortonePayResponseDTO portonePayResponseDTO
                = portoneClient.postSubscribePaymentOnetime("Bearer " + redisService.getDataFromRedis("Access-Token"), portonePayRequestDTO);

        if(portonePayResponseDTO.getCode() == HttpStatus.UNAUTHORIZED.value()){
            AccessTokenResponseDTO accessTokenResponseDTO = getAccessToken();
            redisService.saveDataToRedis("Access-Token", accessTokenResponseDTO.getResponse().getAccessToken());
            portonePayResponseDTO
                    = portoneClient.postSubscribePaymentOnetime("Bearer " + redisService.getDataFromRedis("Access-Token"), portonePayRequestDTO);
        }
        return portonePayResponseDTO;

    }
    public AccessTokenResponseDTO getAccessToken() {
        return portoneClient.postAccessToken(AccessTokenRequestDTO.of(impKey, impSecret));
    }

    // 카드 정보 등록
    public UserCard enrollUserCard(UserRequestDTO userRequestDTO) {
        return userCardRepository.save(userRequestDTO);
    }

    // 회원 보유 카드 정보 조회
    public List<UserCard> selectUserCardList(UserRequestDTO userRequestDTO) {
        return userCardRepository.findUserCardById(userRequestDTO.getUserId());
    }

    // 카드 정보 수정(1)
    public UserCard modifyUserCard(UserRequestDTO userRequestDTO) {
        UserCard userCard = entityManager.find(UserCard.class, userRequestDTO.getCardId());
        userCard.setCardNumber(userRequestDTO.getCardStatus());
        userCard.setCardStatus(userRequestDTO.getCardStatus());
        userCard.setPwd2digit(userRequestDTO.getCardStatus());
        entityManager.persist(userCard);
        return userCard;
    }
    // 카드 정보 수정(2)
    public UserCard modifyCardStatusUserCard(UserRequestDTO userRequestDTO) {
        return userCardRepository.modifyCardStatusByCardId(userRequestDTO.getCardStatus(), userRequestDTO.getCardId())
                .orElseThrow(() -> new EntityNotFoundException("userCard not found with id: " + userRequestDTO.getCardId()));
    }

    // 카드 정보 조회(활성화만)
    public Page<CardOrder> selectOrderHistory(UserRequestDTO userRequestDTO, int page) {
        return cardOrderRepository.selectOrderHistory(userRequestDTO.getUserId(), PageRequest.of(page, 10)
                , PageRequest.of(0, 10, Sort.by("createdAt").ascending()));
    }
}
