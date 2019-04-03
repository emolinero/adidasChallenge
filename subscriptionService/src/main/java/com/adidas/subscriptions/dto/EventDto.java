package com.adidas.subscriptions.dto;

public class EventDto {

  private Long id;
  private String description;
  private Long newsletterId;

  public Long getId() {
    return id;
  }

  public EventDto setId(Long id) {
    this.id = id;
    return this;
  }

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
}
