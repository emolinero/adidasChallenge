package com.adidas.eventService.dto;

public class EventDto {

    private Long id;
    private String description;
    private Long newsletterId;

    public String getDescription() {
        return description;
    }

    public EventDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getNewsletterId() {
        return newsletterId;
    }

    public EventDto setNewsletterId(Long newsletterId) {
        this.newsletterId = newsletterId;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
