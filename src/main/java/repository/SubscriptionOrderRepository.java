package repository;

import entity.Subscription;
import entity.SubscriptionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionOrderRepository extends JpaRepository<SubscriptionOrder, String> {

    Subscription save(Subscription subscription);
}
