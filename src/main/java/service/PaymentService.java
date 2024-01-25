package service;

import dto.*;
import entity.User;
import entity.UserCard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import repository.PaymentRepository;
import service.feign.PortoneFeign;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    private final PortoneFeign portoneFeign;

    private final PaymentRepository paymentRepository;

    @Value("${portone.imp.key}")
    private String imp_key;
    @Value("${portone.imp.secret}")
    private String imp_secret;
    @Value("${portone.imp.merchant_uid}")
    private String merchant_uid;

    public PaymentService(PortoneFeign portoneFeign, PaymentRepository paymentRepository){
        this.portoneFeign = portoneFeign;
        this.paymentRepository = paymentRepository;
    }

    public PaymentResponseDTO pay(PaymentRequestDTO paymentRequestDTO) {
        AccessTokenResponseDTO accessTokenResponseDTO = getAccessToken();
        UserCard userCard = paymentRepository.findCardByCardId(paymentRequestDTO.getCardId());
        User user = paymentRepository.findUserByUserId(paymentRequestDTO.getUserId());

        SubscribeRequestDTO subscribeRequestDTO= SubscribeRequestDTO.builder()
                .cardNumber(userCard.getCardNumber())
                .expiry((userCard.getExpiry()))
                .amount(paymentRequestDTO.getAmount())
                .birth(user.getBirth())
                .merchantUid(merchant_uid)
                .token(accessTokenResponseDTO.getResponse().getAccess_token())
                .build();

        SubscribeResponseDTO subscribeResponseDTO = portoneFeign.postSubscribePaymentOnetime(subscribeRequestDTO);

        PaymentResponseDTO paymentResponseDTO = PaymentResponseDTO.builder(
                subscribeResponseDTO.getCode(),
                subscribeResponseDTO.getMessage(),
                subscribeResponseDTO.getResponse()
        ).build();

        return paymentResponseDTO;
    }
    public AccessTokenResponseDTO getAccessToken() {
        return portoneFeign.postAccessToken(AccessTokenRequestDTO.of(imp_key, imp_secret));
    }
    /*
     - 보유 카드가 없으면 에러 응답
     - 보유 카드가 존재하면 포트원으로 결제 요청 & 거래 내역 적재
     - 포트원으로 결제 요청후 응답
    */
    public List<UserCard> findUserCard(PaymentRequestDTO paymentRequestDTO) {
        User user = paymentRepository.findUserById(paymentRequestDTO.getUserId());
        return paymentRepository.findUserCardByUserId(user.getId());
    }
    public User findUser(PaymentRequestDTO paymentRequestDTO) {
        return paymentRepository.findUserById(paymentRequestDTO.getUserId());
    }

}
