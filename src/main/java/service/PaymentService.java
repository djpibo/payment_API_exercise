package service;

import dto.*;
import entity.User;
import entity.UserCard;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import repository.PaymentRepository;
import service.feign.PortoneFeign;

import java.util.List;

@Service
public class PaymentService {
    private final PortoneFeign portoneFeign;

    private final PaymentRepository paymentRepository;

    @Value("${portone.imp.key}")
    private String impKey;
    @Value("${portone.imp.secret}")
    private String impSecret;
    @Value("${portone.imp.merchant_uid}")
    private String merchantUid;

    public PaymentService(PortoneFeign portoneFeign, PaymentRepository paymentRepository){
        this.portoneFeign = portoneFeign;
        this.paymentRepository = paymentRepository;
    }
    public List<UserCard> findUserCard(PaymentRequestDTO paymentRequestDTO) {
        User user = paymentRepository.findUserById(paymentRequestDTO.getUserId());
        return paymentRepository.findUserCardByUserId(user.getId());
    }
    public User findUser(PaymentRequestDTO paymentRequestDTO) {
        return paymentRepository.findUserById(paymentRequestDTO.getUserId());
    }

    public SubscribeResponseDTO pay(PaymentRequestDTO paymentRequestDTO) {
        AccessTokenResponseDTO accessTokenResponseDTO = getAccessToken();
        UserCard userCard = paymentRepository.findCardByCardId(paymentRequestDTO.getCardId());
        User user = paymentRepository.findUserByUserId(paymentRequestDTO.getUserId());

        SubscribeRequestDTO subscribeRequestDTO= SubscribeRequestDTO.builder()
                .cardNumber(userCard.getCardNumber())
                .expiry((userCard.getExpiry()))
                .amount(paymentRequestDTO.getAmount())
                .birth(user.getBirth())
                .merchantUid(merchantUid)
                .token(accessTokenResponseDTO.getResponse().getAccessToken())
                .build();

        return portoneFeign.postSubscribePaymentOnetime(subscribeRequestDTO);

    }
    public AccessTokenResponseDTO getAccessToken() {
        return portoneFeign.postAccessToken(AccessTokenRequestDTO.of(impKey, impSecret));
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
        return paymentRepository.modifyByCardId(paymentRequestDTO.getCardId())
                .orElseThrow(() -> new EntityNotFoundException("userCard not found with id: " + paymentRequestDTO.getCardId()));
    }
}
