package repository;

import dto.PaymentRequestDTO;
import entity.CardTransactionHistory;
import entity.User;
import entity.UserCard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<CardTransactionHistory, String> {

    // 조회
    User findUserById(long id);
    List<UserCard> findUserCardByUserId(long userId);

    /* @Query 사용하기 */
    @Query("SELECT * FROM USER_CARD WHERE phone_number = ?")
    List<CardTransactionHistory> findCardListsByPhoneNumber(String phone_number);
    UserCard findCardByCardId(Long cardId);
    User findUserByUserId(long userId);
    List<UserCard> findUserCardById(long userId);
    UserCard save(PaymentRequestDTO paymentRequestDTO);
    @Modifying
    @Query("UPDATE USER_CARD SET WHERE card_id = ?")
    Optional<UserCard> modifyByCardId(long cardId);
}
