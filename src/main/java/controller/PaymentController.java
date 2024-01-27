package controller;

import common.exception.NoSuchElementFoundException;
import dto.UserRequestDTO;
import dto.PortonePayResponseDTO;
import entity.CardOrder;
import entity.UserCard;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
  public ResponseEntity<PortonePayResponseDTO> pay(@RequestBody UserRequestDTO userRequestDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentService.pay(userRequestDTO));
  }

  @PostMapping("/user/enroll")
  public ResponseEntity<UserCard> enrollUserCard(@RequestBody UserRequestDTO userRequestDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentService.enrollUserCard(userRequestDTO));
  }

  @PostMapping("/user/select")
  public ResponseEntity<List<UserCard>> getUserCardList(@RequestBody UserRequestDTO userRequestDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentService.selectUserCardList(userRequestDTO));
  }

  @PostMapping("/user/modify")
  public ResponseEntity<UserCard> modifyUserCard(@RequestBody UserRequestDTO userRequestDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentService.modifyUserCard(userRequestDTO));
  }

  @PostMapping("/user/data")
  public ResponseEntity<Page<CardOrder>> getOrderHistory(@RequestBody UserRequestDTO userRequestDTO, @RequestParam(defaultValue = "0") int page) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentService.selectOrderHistory(userRequestDTO, page));
  }

  @PostMapping("/user/subscribe")
  public ResponseEntity<?> postSubscribe(@RequestBody UserRequestDTO userRequestDTO, @RequestParam(defaultValue = "0") int page) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentService.selectOrderHistory(userRequestDTO, page));
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
