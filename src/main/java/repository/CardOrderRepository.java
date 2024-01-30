package repository;

import entity.CardOrder;
import entity.Member;
import entity.UserCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardOrderRepository extends JpaRepository<CardOrder, String> {
    UserCard findCardByCardId(Long cardId);
    Member findUserByUserId(long userId);
    CardOrder findCardOrderByImpUid(String orderId);
    @Query("SELECT * FROM CARD_ORDER WHERE STATUS = ACTIVE AND customerUid =:userId")
    Page<CardOrder> selectOrderHistory(long userId, PageRequest of, Pageable pageable);
    @Modifying
    @Query("UPDATE CARD_ORDER SET STATUS = CANCELLED WHERE STATUS = ACTIVE AND impUid =:impUid")
    void cancelOrderByimpUid(String impUid);
}
