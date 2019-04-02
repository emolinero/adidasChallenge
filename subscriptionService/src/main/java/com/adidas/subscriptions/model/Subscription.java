package com.adidas.subscriptions.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "subscriptions")
public class Subscription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @OneToOne
    private Customer customer;

    @OneToOne
    private Newsletter newsletter;
    private boolean consent;

    public Long getId() {
        return Id;
    }

    public Subscription setId(Long id) {

        Id = id;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Subscription setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Newsletter getNewsletter() {
        return newsletter;
    }

    public Subscription setNewsletter(Newsletter newsletter) {

        this.newsletter = newsletter;
        return this;
    }

    public boolean isConsent() {
        return consent;
    }

    public Subscription setConsent(boolean consent) {

        this.consent = consent;
        return this;
    }


}
