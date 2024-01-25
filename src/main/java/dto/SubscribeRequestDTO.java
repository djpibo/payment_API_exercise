package dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SubscribeRequestDTO {

    // required
    private BigDecimal amount;
    private String merchantUid;
    private String cardNumber;
    private String expiry;
    private String birth;
    private String token;

    // optional
    private String currency;
    private BigDecimal taxFree;
    private BigDecimal vatAmount;
    private String pwd2digit;
    private String cvc;
    private String customerUid;
    private String pg;
    private String name; // 주문명
    private String buyerName;
    private String buyerEmail;
    private String buyerTel;
    private String buyerAddr;
    private String buyerPostcode;
    private int cardQuota;
    private boolean interestFreeByMerchant;
    private boolean useCardPoint;
    private String customData;
    private String noticeUrl;
    private String browserIp;
    private String secure3dChargeId;
    private String secure3dToken;
    private String productType;
}
