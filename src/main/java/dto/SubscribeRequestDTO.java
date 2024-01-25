package dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SubscribeRequestDTO {

    // required
    private BigDecimal amount;
    private String merchant_uid;
    private String card_number;
    private String expiry;
    private String birth;
    private String token;

    public static class Builder {
        private final SubscribeRequestDTO subscribeRequestDTO;
        public Builder() {
            subscribeRequestDTO = new SubscribeRequestDTO();
        }
        public Builder amount(BigDecimal amount) {
            subscribeRequestDTO.amount = amount;
            return this;
        }
        public Builder merchantUid(String merchantUid) {
            subscribeRequestDTO.merchant_uid = merchantUid;
            return this;
        }
        public Builder cardNumber(String cardNumber) {
            subscribeRequestDTO.card_number = cardNumber;
            return this;
        }
        public Builder expiry(String expiry) {
            subscribeRequestDTO.expiry = expiry;
            return this;
        }
        public Builder birth(String birth) {
            subscribeRequestDTO.birth = birth;
            return this;
        }
        public Builder token(String token) {
            subscribeRequestDTO.token = token;
            return this;
        }
        public SubscribeRequestDTO build() {
            return subscribeRequestDTO;
        }
    }
    public static Builder builder() {
        return new Builder();
    }

    // optional
    private String currency;
    private BigDecimal tax_free;
    private BigDecimal vat_amount;
    private String pwd_2digit;
    private String cvc;
    private String customer_uid;
    private String pg;
    private String name; // 주문명
    private String buyer_name;
    private String buyer_email;
    private String buyer_tel;
    private String buyer_addr;
    private String buyer_postcode;
    private int card_quota;
    private boolean interest_free_by_merchant;
    private boolean use_card_point;
    private String custom_data;
    private String notice_url;
    private String browser_ip;
    private String secure_3d_charge_id;
    private String secure_3d_token;
    private String product_type;
}
