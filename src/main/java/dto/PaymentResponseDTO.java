package dto;

import lombok.Data;

@Data
public class PaymentResponseDTO {
    private User user;
    private String amount;
    private String card;

    public class User {
        private String phoneNumber;
        private String userName;
    }

}

