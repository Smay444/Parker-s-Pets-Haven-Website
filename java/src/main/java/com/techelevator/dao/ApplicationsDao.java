package com.techelevator.dao;

import com.techelevator.model.Applications;

import java.util.List;

public interface ApplicationsDao {
    Applications getAppByAppId(int applicationId);
    List<Applications> getAppsByUserId(int userId);
    int createApplication(Applications applications);
    Applications approve(int applicationId, String admin);
    Applications reject(int applicationId, String admin);
    List<Applications> getAllVolunteers();
    List<Applications> getAllPending();
    String promoteToAdmin(int userId);
    List<Applications> getAllApplications();
    String promoteToVolunteer(int userId);


}
