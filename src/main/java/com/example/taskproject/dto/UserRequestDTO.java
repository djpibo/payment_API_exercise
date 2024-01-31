package com.example.taskproject.dto;

import com.example.taskproject.entity.Status;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
public class UserRequestDTO {
    private long userId;
    private long cardId;
    private long orderId;
    private String pwd2digit;
    private String phoneNumber;
    private String userName;
    private String cardName;
    private String cardNumber;
    @Enumerated(EnumType.STRING)
    private Status cardStatus;
    @Enumerated(EnumType.STRING)
    private String userStatus;
    private BigDecimal amount;
}

