package repository;

import dto.PaymentRequestDTO;
import entity.CardOrder;
import entity.CardTransactionHistory;
import entity.Member;
import entity.UserCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<CardTransactionHistory, String> {

    Member findUserById(long id);
    List<UserCard> findUserCardByUserId(long userId);

    /* @Query 사용하기 */
    @Query("SELECT * FROM USER_CARD WHERE phone_number = ?")
    List<CardTransactionHistory> findCardListsByPhoneNumber(String phone_number);
    UserCard findCardByCardId(Long cardId);
    Member findUserByUserId(long userId);
    List<UserCard> findUserCardById(long userId);
    UserCard save(PaymentRequestDTO paymentRequestDTO);
    @Modifying
    @Query("UPDATE USER_CARD SET status = ? WHERE card_id = ?")
    Optional<UserCard> modifyCardStatusByCardId(String status, long cardId);


    @Query("SELECT * FROM CARD_ORDER WHERE STATUS = ACTIVE AND customerUid =:customerUid")
    Page<CardOrder> selectOrderHistory(long customerUid, PageRequest of, Pageable pageable);
}
