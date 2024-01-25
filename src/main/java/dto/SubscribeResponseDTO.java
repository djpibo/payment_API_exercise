package dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
public class SubscribeResponseDTO {
    private int code;
    private String message;
    private PaymentAnnotation response;

    @Data
    @RequiredArgsConstructor
    public class PaymentAnnotation{

        // required
        private String imp_uid;
        private String merchant_uid;
        private BigDecimal amount;
        private BigDecimal cancel_amount;
        private String currency;
        private String status;  // ready:미결제 paid:결제완료 cancelled:결제취소 failed:결제실패

        // optional
        private String pay_method;
        private String channel;
        private String pg_provider;
        private String emb_pg_provider;
        private String pg_tid;
        private String pg_id;
        private String escrow;
        private String apply_num;
        private String bank_code;
        private String bank_name;
        private String card_code;
        private String card_name;
        private String card_number;
        private int card_quota;
        private int card_type;
        private String vbank_code;
        private String vbank_num;
        private String vbank_name;
        private String vbank_holder;
        private int vbank_issued_at;
        private int vbank_date;
        private String name;
        private String buyer_name;
        private String buyer_email;
        private String buyer_tel;
        private String buyer_addr;
        private String buyer_postcode;
        private String custom_data;
        private String user_agent;
        private int started_at;
        private int paid_at;
        private int failed_at;
        private int cancelled_at;
        private String fail_reason;
        private String cancel_reason;
        private String receipt_url;
        private boolean cash_receipt_issued;
        private String customer_uid;
        private String customer_uid_usage;
        private PaymentCancelAnnotation[] cancel_history;
    }

    @Data
    public class PaymentCancelAnnotation{
        private String pg_tid;
        private BigDecimal amount;
        private int cancelled_at;
        private String reason;
        private String receipt_url;
    }
}
