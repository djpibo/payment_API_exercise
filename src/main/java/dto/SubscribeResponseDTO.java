package dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
public class SubscribeResponseDTO {
    private int code;
    private String message;
    private PaymentAnnotation response;

    @Data
    public static class PaymentAnnotation{

        // required
        private String impUid;
        private String merchantUid;
        private BigDecimal amount;
        private BigDecimal cancelAmount;
        private String currency;
        private String status;  // ready:미결제 paid:결제완료 cancelled:결제취소 failed:결제실패

        // optional
        private String payMethod;
        private String channel;
        private String pgProvider;
        private String embPgProvider;
        private String pgTid;
        private String pgId;
        private String escrow;
        private String applyNum;
        private String bankCode;
        private String bankName;
        private String cardCode;
        private String cardName;
        private String cardNumber;
        private int cardQuota;
        private int cardType;
        private String vbankCode;
        private String vbankNum;
        private String vbankName;
        private String vbankHolder;
        private int vbankIssuedAt;
        private int vbankDate;
        private String name;
        private String buyerName;
        private String buyerEmail;
        private String buyerTel;
        private String buyerAddr;
        private String buyerPostcode;
        private String customData;
        private String userAgent;
        private int startedAt;
        private int paidAt;
        private int failedAt;
        private int cancelledAt;
        private String failReason;
        private String cancelReason;
        private String receiptUrl;
        private boolean cashReceiptIssued;
        private String customerUid;
        private String customerUidUsage;
        private PaymentCancelAnnotation[] cancelHistory;
    }

    @Data
    public static class PaymentCancelAnnotation{
        private String pgTid;
        private BigDecimal amount;
        private int cancelledAt;
        private String reason;
        private String receiptUrl;
    }
}
