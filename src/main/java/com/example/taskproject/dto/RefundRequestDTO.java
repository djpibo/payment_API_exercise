package com.example.taskproject.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RefundRequestDTO {
    private String impUid;
    private String merchantUid;
    private BigDecimal amount;
    private BigDecimal taxFree;
    private BigDecimal vatAmount;
    private BigDecimal checksum;
    private String reason;
    private String refundHolder;
    private String refundBank;
    private String refundAccount;
    private String refundTel;
    private String[] extra;
}
