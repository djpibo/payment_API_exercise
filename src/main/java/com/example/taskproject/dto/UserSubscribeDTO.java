package com.example.taskproject.dto;

import com.example.taskproject.entity.Status;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserSubscribeDTO {
    private Long userId;
    private Long cardId;
    private LocalDate paidDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime appliedStartDate;
    private LocalDateTime appliedEndDate;
    private int payCount;
    private BigDecimal payAmount;
}
