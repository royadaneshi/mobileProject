package com.edufire.dic3.Models;

import com.edufire.dic3.PremiumRequest;
import com.edufire.dic3.Status;

import java.util.HashMap;

public class Admin extends User {
    HashMap<User, PremiumRequest> premiumRequests = new HashMap<>();

    public Admin(String username, String password) {
        super(username, password);
    }

    public void sendRequestToAdmin(PremiumRequest premiumRequest) {
        premiumRequests.put(premiumRequest.getUser(), premiumRequest);
    }

    public void acceptRequest(PremiumRequest premiumRequest) {
        premiumRequest.setStatus(Status.Accepted);
        premiumRequest.getUser().setUserPremium(true);

    }

    public void rejectRequest(PremiumRequest premiumRequest) {
        premiumRequest.setStatus(Status.Rejected);
        premiumRequest.getUser().setUserPremium(false);

    }
}
