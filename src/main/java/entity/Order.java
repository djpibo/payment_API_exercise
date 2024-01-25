package entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String merchant_uid;
    @Column(nullable = false)
    private BigDecimal amount;
    private BigDecimal tax_free;
    private BigDecimal vat_amount;
    private String customer_uid;
    private String pg;
    private String name;
    private String custom_data;
    private String browser_ip;
    private String notice_url;
    private String secure_3d_charge_id;
    private String secure_3d_token;
    private String product_type;
    private int card_quota;
    private boolean use_card_point;
    private boolean interest_free_by_merchant;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;
}

