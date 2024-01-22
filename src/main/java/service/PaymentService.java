package service;

import dto.PaymentRequestDTO;
import dto.PaymentResponseDTO;
import entity.User;
import entity.UserCard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentResponseDTO paymentResponseDTO;
    public PaymentService(PaymentResponseDTO paymentResponseDTO){
        this.paymentResponseDTO = paymentResponseDTO;
    }

    public PaymentResponseDTO pay(PaymentRequestDTO paymentRequestDTO) {
        return paymentResponseDTO;
    }
    public List<UserCard> findUserCards(User user){
        return new ArrayList<>();
    }
}
