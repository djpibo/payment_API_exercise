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
import repository.PaymentRepository;
import service.feign.PortoneClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PortoneClient portoneClient;
    private final EntityManager entityManager;
    private final PaymentRepository paymentRepository;
    private final RedisService redisService;

    @Value("${portone.imp.key}")
    private String impKey;
    @Value("${portone.imp.secret}")
    private String impSecret;
    @Value("${portone.imp.merchant_uid}")
    private String merchantUid;
    public List<UserCard> findUserCard(PaymentRequestDTO paymentRequestDTO) {
        Member member = paymentRepository.findUserById(paymentRequestDTO.getUserId());
        return paymentRepository.findUserCardByUserId(member.getId());
    }
    public Member findUser(PaymentRequestDTO paymentRequestDTO) {
        return paymentRepository.findUserById(paymentRequestDTO.getUserId());
    }

    public SubscribeResponseDTO pay(PaymentRequestDTO paymentRequestDTO) {
        UserCard userCard = paymentRepository.findCardByCardId(paymentRequestDTO.getCardId());
        Member member = paymentRepository.findUserByUserId(paymentRequestDTO.getUserId());

        SubscribeRequestDTO subscribeRequestDTO= SubscribeRequestDTO.builder()
                .cardNumber(userCard.getCardNumber())
                .expiry((userCard.getExpiry()))
                .amount(paymentRequestDTO.getAmount())
                .birth(member.getBirth())
                .merchantUid(merchantUid)
                .build();
        SubscribeResponseDTO subscribeResponseDTO
                = portoneClient.postSubscribePaymentOnetime("Bearer " + redisService.getDataFromRedis("Access-Token"), subscribeRequestDTO);

        if(subscribeResponseDTO.getCode() == HttpStatus.UNAUTHORIZED.value()){
            AccessTokenResponseDTO accessTokenResponseDTO = getAccessToken();
            redisService.saveDataToRedis("Access-Token", accessTokenResponseDTO.getResponse().getAccessToken());
            subscribeResponseDTO
                    = portoneClient.postSubscribePaymentOnetime("Bearer " + redisService.getDataFromRedis("Access-Token"), subscribeRequestDTO);
        }
        return subscribeResponseDTO;

    }
    public AccessTokenResponseDTO getAccessToken() {
        return portoneClient.postAccessToken(AccessTokenRequestDTO.of(impKey, impSecret));
    }
    /*
     - 보유 카드가 없으면 에러 응답
     - 보유 카드가 존재하면 포트원으로 결제 요청 & 거래 내역 적재
     - 포트원으로 결제 요청후 응답
    */

    public UserCard enrollUserCard(PaymentRequestDTO paymentRequestDTO) {
        return paymentRepository.save(paymentRequestDTO);
    }

    public List<UserCard> selectUserCardList(PaymentRequestDTO paymentRequestDTO) {
        return paymentRepository.findUserCardById(paymentRequestDTO.getUserId());
    }

    public UserCard modifyUserCard(PaymentRequestDTO paymentRequestDTO) {
        UserCard userCard = entityManager.find(UserCard.class, paymentRequestDTO.getCardId());
        userCard.setCardNumber(paymentRequestDTO.getCardStatus());
        userCard.setCardStatus(paymentRequestDTO.getCardStatus());
        userCard.setPwd2digit(paymentRequestDTO.getCardStatus());
        return userCard;
    }
    public UserCard modifyCardStatusUserCard(PaymentRequestDTO paymentRequestDTO) {
        return paymentRepository.modifyCardStatusByCardId(paymentRequestDTO.getCardStatus(), paymentRequestDTO.getCardId())
                .orElseThrow(() -> new EntityNotFoundException("userCard not found with id: " + paymentRequestDTO.getCardId()));
    }

    public Page<CardOrder> selectOrderHistory(PaymentRequestDTO paymentRequestDTO, int page) {
        return paymentRepository.selectOrderHistory(paymentRequestDTO.getUserId(), PageRequest.of(page, 10)
                , PageRequest.of(0, 10, Sort.by("createdAt").ascending()));
    }
}
