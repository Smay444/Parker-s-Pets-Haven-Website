package com.techelevator.controller;

import com.techelevator.dao.ApplicationsDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class ApplicationsController {
    @Autowired
    private ApplicationsDao applicationsDao;
    @Autowired
    private UserDao userDao;

    private static final String API_BASE_PATH = "/Applications";
    private static final DateTimeFormatter LOG_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @RequestMapping(path = API_BASE_PATH + "/{applicantId}", method = RequestMethod.GET)
    public String getAppStatusById(@PathVariable int applicantId, Principal principal){
        String status = "";
        Applications applications = applicationsDao.getAppByAppId(applicantId);
        status += formatApplication(applications);
        return status;
    }

    @RequestMapping(path = API_BASE_PATH, method = RequestMethod.GET)
    public List<Applications> applicationsDirectory(){
        return applicationsDao.getAllApplications();
    }
    @RequestMapping(path = API_BASE_PATH + "volunteers", method = RequestMethod.GET)
    public List<Applications> volunteersDirectory(){
        return applicationsDao.getAllVolunteers();
    }

    @RequestMapping(path = API_BASE_PATH + "/apply", method = RequestMethod.POST)
    public String applyToBeVolunteer(@RequestBody Applications applications, Principal principal){
        String username = principal.getName();
        int userId = userDao.getUserIdByName(username);
        applications.setUserId(userId);
        applicationsDao.createApplication(applications);
        String success = "Your application has been submitted";
        return success;
    }
    @RequestMapping(path = API_BASE_PATH + "/{applicantId}" + "/approve", method = RequestMethod.PUT)
    public String approveVolunteer(@PathVariable int applicantId,Principal principal){
        applicationsDao.approve(applicantId,principal.getName());
        Applications applications = applicationsDao.getAppByAppId(applicantId);
        applicationsDao.promoteToVolunteer(applications.getUserId());
        String approved = "The application for " + applications.getAppName() + " has been approved!";
        return approved;
    }
    @RequestMapping(path = API_BASE_PATH + "/{applicantId}" + "/reject", method = RequestMethod.PUT)
    public String rejectVolunteer(@PathVariable int applicantId,Principal principal){
        applicationsDao.reject(applicantId,principal.getName());
        Applications applications = applicationsDao.getAppByAppId(applicantId);
        String rejected = "The application for " + applications.getAppName() + " has been rejected.";
        return rejected;
    }
    @RequestMapping(path = API_BASE_PATH + "/pending", method = RequestMethod.GET)
    public List<Applications> pendingList(){
        return applicationsDao.getAllPending();
    }
    @RequestMapping(path = API_BASE_PATH + "/promote/" + "{userId}", method = RequestMethod.PUT)
    public String promoteVolunteer(@PathVariable int userId){
        return applicationsDao.promoteToAdmin(userId);
    }



    private String formatApplication(Applications applications){
        String str = "Your application that was created on ";
        String date = LOG_FORMAT.format(applications.getCreatedDate());
        String decision = applications.getStatus();
        String admin = applications.getApprovedBy();
        if(Objects.equals(applications.getStatus(), "pending")){
            str += date + " is currently " + decision;
        } else {
            str += date + " has been " + decision + " by " + admin;
        }
        return str;
    }

}
