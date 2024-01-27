package dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserRequestDTO {
    private long userId;
    private long cardId;
    private long orderId;
    private String phoneNumber;
    private String userName;
    private String cardName;
    @Enumerated(EnumType.STRING)
    private String cardStatus;
    @Enumerated(EnumType.STRING)
    private String userStatus;
    private BigDecimal amount;
}

