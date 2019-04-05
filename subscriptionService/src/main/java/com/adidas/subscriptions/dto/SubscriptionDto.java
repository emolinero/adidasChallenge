package com.adidas.subscriptions.dto;

import java.util.Date;

public class SubscriptionDto {

    private String email;
    private String firstName;
    private String gender;
    private Date dateOfBirth;
    private boolean consent;
    private Long newsletterId;

    public String getEmail() {
        return email;
    }

    public SubscriptionDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public SubscriptionDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public SubscriptionDto setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public SubscriptionDto setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public boolean isConsent() {
        return consent;
    }

    public SubscriptionDto setConsent(boolean consent) {
        this.consent = consent;
        return this;
    }

    public Long getNewsletterId() {
        return newsletterId;
    }

    public SubscriptionDto setNewsletterId(Long newsletterId) {
        this.newsletterId = newsletterId;
        return this;
    }
}
