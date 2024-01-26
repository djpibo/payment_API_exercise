package controller;

import common.exception.NoSuchElementFoundException;
import dto.PaymentRequestDTO;
import dto.SubscribeResponseDTO;
import entity.CardOrder;
import entity.UserCard;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/domain")
@RequiredArgsConstructor
public class PaymentController {
  @Autowired
  private final PaymentService paymentService;

  // 내부 시스템에서 플랫폼을 호출
  @GetMapping("/payment")
  public ResponseEntity<SubscribeResponseDTO> pay(@RequestBody PaymentRequestDTO paymentRequestDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentService.pay(paymentRequestDTO));
  }

  @PostMapping("/user/enroll")
  public ResponseEntity<UserCard> enrollUserCard(@RequestBody PaymentRequestDTO paymentRequestDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentService.enrollUserCard(paymentRequestDTO));
  }

  @PostMapping("/user/select")
  public ResponseEntity<List<UserCard>> getUserCardList(@RequestBody PaymentRequestDTO paymentRequestDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentService.selectUserCardList(paymentRequestDTO));
  }

  @PostMapping("/user/modify")
  public ResponseEntity<UserCard> modifyUserCard(@RequestBody PaymentRequestDTO paymentRequestDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentService.modifyUserCard(paymentRequestDTO));
  }

  @PostMapping("/user/data")
  public ResponseEntity<Page<CardOrder>> getOrderHistory(@RequestBody PaymentRequestDTO paymentRequestDTO, @RequestParam(defaultValue = "0") int page) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentService.selectOrderHistory(paymentRequestDTO, page));
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
