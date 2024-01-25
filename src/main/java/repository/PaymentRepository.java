package repository;

import entity.CardTransactionHistory;
import entity.User;
import entity.UserCard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<CardTransactionHistory, String> {

    /* 쿼리 메소드의 주제 키워드 */

    // 조회
    User findUserById(long id);

    List<UserCard> findUserCardByUserId(long userId);

    /* @Query 사용하기 */

    @Query("SELECT * FROM USER_CARD WHERE phone_number = ?")
    List<CardTransactionHistory> findCardListsByPhoneNumber(String phone_number);

    @Query(value = "SELECT * FROM product p WHERE p.price > 2000", nativeQuery = true)
    List<CardTransactionHistory> findByPriceBasisNativeQuery();

    @Query("SELECT p FROM Product p WHERE p.price > ?1")
    List<CardTransactionHistory> findByPriceWithParameter(Integer price);

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<CardTransactionHistory> findByPriceWithParameterNaming(Integer price);

    @Query("SELECT p FROM Product p WHERE p.price > :pri")
    List<CardTransactionHistory> findByPriceWithParameterNaming2(@Param("pri") Integer price);

    @Query(value = "SELECT * FROM product WHERE price > :price",
    countQuery = "SELECT count(*) FROM product WHERE price > ?1",
    nativeQuery = true)
    List<CardTransactionHistory> findByPriceWithParameterPaging(Integer price, Pageable pageable);

    UserCard findCardByCardId(Long cardId);

    User findUserByUserId(long userId);
}
