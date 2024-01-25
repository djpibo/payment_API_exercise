package controller;

import common.exception.NoSuchElementFoundException;
import dto.PaymentRequestDTO;
import dto.PaymentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import service.PaymentService;

@RestController
@RequestMapping("/domain")
public class PaymentController {
  @Autowired
  private final PaymentService paymentService;

  public PaymentController(PaymentService paymentService){
    this.paymentService = paymentService;
  }

  // 내부 시스템에서 플랫폼을 호출
  @GetMapping("/payment")
  public ResponseEntity<PaymentResponseDTO> pay(@RequestBody PaymentRequestDTO paymentRequestDTO) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(paymentService.pay(paymentRequestDTO));
  }

  @ExceptionHandler(NoSuchElementFoundException.class)
  public ResponseEntity<ErrorResponse> handleNoSuchElementFoundException(NoSuchElementFoundException exception) {
    ErrorResponse errorResponse = ErrorResponse.builder(exception, HttpStatus.NOT_FOUND, "The requested resource could not be found.").build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleAllUncaughtException(Exception exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

}
