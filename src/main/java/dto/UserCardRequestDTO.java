package dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserCardRequestDTO {
    private long userId;
    private long cardId;
    private String phoneNumber;
    private String userName;
    private BigDecimal amount;
}

