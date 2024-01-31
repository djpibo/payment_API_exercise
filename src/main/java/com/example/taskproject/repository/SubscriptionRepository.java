package com.example.taskproject.repository;

import com.example.taskproject.dto.UserSubscribeDTO;
import com.example.taskproject.entity.Status;
import com.example.taskproject.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
    @Modifying
    @Query("UPDATE Subscription " +
            "SET paidDate =:paidDate, status =:status, appliedEndDate =:appliedEndDate, payCount =:payCount, payAmount =:payAmount" +
            "WHERE card_id =:id")
    Subscription modifyByCardId(@Param("paidDate") LocalDate paidDate
            , @Param("status") Status status
            , @Param("appliedEndDate") LocalDateTime appliedEndDate
            , @Param("payCount") int payCount
            , @Param("payAmount") BigDecimal payAmount
            , @Param("id") long id);
    Subscription insertByCardId(UserSubscribeDTO userSubscribeDTO);
    Subscription findSubscriptionByCardId(Long cardId);
    @Modifying
    @Query("UPDATE Subscription " +
            "SET totalPaidCount = totalPaidCount + 1"+
            "WHERE card_id = ?")
    void plusTotalPaidCount(Long id);
}
