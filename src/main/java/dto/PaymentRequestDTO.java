package dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {
    private long userId;
    private long cardId;
    private String phoneNumber;
    private String userName;
    private BigDecimal amount;
}

