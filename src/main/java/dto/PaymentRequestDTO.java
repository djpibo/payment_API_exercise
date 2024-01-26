package dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {
    private long userId;
    private long cardId;
    private String phoneNumber;
    private String userName;
    private String cardName;
    @Enumerated(EnumType.STRING)
    private String cardStatus;
    private BigDecimal amount;
}

