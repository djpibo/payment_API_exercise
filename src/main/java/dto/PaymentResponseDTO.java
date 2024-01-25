package dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
public class PaymentResponseDTO {
    private int code;
    private String message;
    private PaymentAnnotation response;
    @Data
    @RequiredArgsConstructor
    public class PaymentAnnotation {
        private String imp_uid;
        private String merchant_uid;
        private BigDecimal amount;
        private BigDecimal cancel_amount;
        private String currency;
        private String status;  // ready:미결제 paid:결제완료 cancelled:결제취소 failed:결제실패
    }
    public static class Builder {
        private final PaymentResponseDTO paymentResponseDTO;
        public Builder(int code, String message, PaymentAnnotation response) {
            paymentResponseDTO = new PaymentResponseDTO();
            paymentResponseDTO.code = code;
            paymentResponseDTO.message = message;
            paymentResponseDTO.response = response;
        }
        public PaymentResponseDTO build() {
            return paymentResponseDTO;
        }
    }

    public static Builder builder(int code, String message, PaymentAnnotation response) {
        return new Builder(code, message, response);
    }

}

