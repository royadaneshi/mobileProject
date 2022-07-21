package com.edufire.dic3;

import com.edufire.dic3.Models.User;

public class PremiumRequest {

    User user;
    Status status;
    String premiumCode;

    public PremiumRequest(User user, String premiumCode) {
        this.user = user;
        this.status = Status.Waiting;
        this.premiumCode = premiumCode;
    }

    public User getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }

    public String getPremiumCode() {
        return premiumCode;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
