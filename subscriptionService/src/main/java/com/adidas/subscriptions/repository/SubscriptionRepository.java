package com.adidas.subscriptions.repository;

import com.adidas.subscriptions.model.Customer;
import com.adidas.subscriptions.model.Newsletter;
import com.adidas.subscriptions.model.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

    Optional<Subscription> findByCustomerAndNewsletter(Customer customer, Newsletter newsletter);
}
