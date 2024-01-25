package dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentResponseDTO {
    private int code;
    private String message;
    private PaymentAnnotation response;

    public static class PaymentAnnotation {
        private String impUid;
        private String merchantUid;
        private BigDecimal amount;
        private BigDecimal cancelAmount;
        private String currency;
        private String status;  // ready:미결제 paid:결제완료 cancelled:결제취소 failed:결제실패
    }
}

