package com.adidas.eventService.model;


import java.io.Serializable;

public class Event implements Serializable {
    private Long id;
    private String description;
    private Long newsletterId;

    public Long getId() {
        return id;
    }

    public Event setId(Long id) {
        this.id = id;
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

//    @Override
//    public String toString() {
//        return "Event{" +
//                "Id=" + id +
//                ", description='" + description + '\'' +
//                ", newsletterId=" + newsletterId +
//                '}';
//    }
}
