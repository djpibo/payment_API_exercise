package com.example.taskproject.repository;

import com.example.taskproject.entity.Subscription;
import com.example.taskproject.entity.SubscriptionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionOrderRepository extends JpaRepository<SubscriptionOrder, String> {

    Subscription save(Subscription subscription);
}
