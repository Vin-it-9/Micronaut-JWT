package org.dto;

import java.time.LocalDateTime;

public class UserActivityDTO {


    private String activityType;
    private String description;
    private LocalDateTime timestamp;

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public UserActivityDTO(String activityType, String description, LocalDateTime timestamp) {
        this.activityType = activityType;
        this.description = description;
        this.timestamp = timestamp;
    }
}