package entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class Subscription{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private LocalDate paidDate;
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    private LocalDate appliedStartDate;
    @Column(nullable = false)
    private LocalDate appliedEndDate;
    @Column(nullable = false)
    private int payCount;
    private int totalPaidCount;
    @Column(nullable = false)
    private BigDecimal payAmount;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;

}

