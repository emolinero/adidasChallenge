package com.adidas.eventService.model;


public class Event {
    private Long Id;
    private String description;
    private Long newsletterId;

    public Long getId() {
        return Id;
    }

    public Event setId(Long id) {
        Id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Event setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getNewsletterId() {
        return newsletterId;
    }

    public Event setNewsletterId(Long newsletterId) {
        this.newsletterId = newsletterId;
        return this;
    }

    @Override
    public String toString() {
        return "Event{" +
                "Id=" + Id +
                ", description='" + description + '\'' +
                ", newsletterId=" + newsletterId +
                '}';
    }
}
