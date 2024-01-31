package com.example.taskproject.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
public class CardOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String impUid;
    @Column(nullable = false)
    private String customerUid;
    @Column(nullable = false)
    private String merchantUid;
    @Column(nullable = false)
    private BigDecimal amount;
    private BigDecimal taxFree;
    private BigDecimal vatAmount;
    private String pg;
    private String name;
    private String customData;
    private String currency;
    private String browserIp;
    private String noticeUrl;
    private String secure3dChargeId;
    private String secure3dToken;
    private String productType;
    private int cardQuota;
    private boolean useCardPoint;
    private boolean interestFreeByMerchant;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;
}

