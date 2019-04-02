package com.adidas.subscriptions.service.impl;

import com.adidas.subscriptions.exceptions.ConstraintsViolationException;
import com.adidas.subscriptions.model.Customer;
import com.adidas.subscriptions.model.Newsletter;
import com.adidas.subscriptions.model.Subscription;
import com.adidas.subscriptions.repository.CustomerRepository;
import com.adidas.subscriptions.repository.NewsletterRepository;
import com.adidas.subscriptions.repository.SubscriptionRepository;
import com.adidas.subscriptions.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional

public class SubscriptionServiceImpl implements SubscriptionService {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    NewsletterRepository newsletterRepository;

    @Override
    public Subscription createSubscription(Subscription subscription) throws Throwable {

        logger.info("Create subscription object with email {}", subscription.getCustomer().getEmail());
        Newsletter existedNewsletter =  newsletterRepository.findById(subscription.getNewsletter().getId()).orElseThrow(() ->  new ConstraintsViolationException("The Newsletter Id does not exist") );
        subscription.setNewsletter(existedNewsletter);

        Customer customer = getCustomer(subscription.getCustomer());
        subscription.setCustomer(customer);

        Optional<Subscription> existedSubscription = subscriptionRepository.findByCustomerAndNewsletter(subscription.getCustomer(),subscription.getNewsletter());

        if(existedSubscription.isPresent()){
            logger.warn("Possible Duplicated Subscription");
            throw new ConstraintsViolationException("Duplicated Key");
        }

        subscription.setNewsletter(existedNewsletter);
        return subscriptionRepository.save(subscription);
    }

    private Customer getCustomer(Customer prototype) {
        Optional<Customer> foundCustomer = customerRepository.findByEmail(prototype.getEmail());
        return (foundCustomer.isPresent()) ? foundCustomer.get() : customerRepository.save(prototype);
    }


}
