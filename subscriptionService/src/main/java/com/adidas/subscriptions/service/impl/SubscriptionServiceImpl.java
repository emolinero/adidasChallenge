package com.adidas.subscriptions.service.impl;

import com.adidas.subscriptions.dto.EmailDto;
import com.adidas.subscriptions.dto.EventDto;
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
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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

    @Autowired
    private ApplicationContext context;

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


    public void eventProcess(EventDto eventDto) {
//            //search for subscription that match the criteria of the event
        //Optional<Subscription> subscriptionRepository.findByNewsletterId(eventDto.getNewsletterId());
//            //send email for each? or send email for the whole?
        String to = new String();
        String message = new String();
        Subscription subscription = new Subscription();
        notifyEmail(new EmailDto(subscription.getCustomer().getEmail(), subscription.getNewsletter().getDescription()));


   }

    private EmailDto notifyEmail(EmailDto emailDto) {
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        logger.info("Sending an email to: {}, with message: {}", emailDto.getTo(), emailDto.getMessage());

        jmsTemplate.convertAndSend("emails", emailDto);
        return emailDto;
    }


}
