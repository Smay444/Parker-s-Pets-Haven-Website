package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Applications {

    private int applicationId;
    private int userId;
    private String appEmail;
    private String appName;
    private String appPhoneNumber;
    private String status;
    private LocalDateTime createdDate;
    private String approvedBy;
    public Applications(){}
    public Applications(int userId,String appEmail, String appName, String appPhoneNumber){
        this.userId = userId;
        this.appEmail = appEmail;
        this.appName = appName;
        this.appPhoneNumber = appPhoneNumber;

    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public String getAppEmail() {
        return appEmail;
    }

    public void setAppEmail(String appEmail) {
        this.appEmail = appEmail;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPhoneNumber() {
        return appPhoneNumber;
    }

    public void setAppPhoneNumber(String appPhoneNumber) {
        this.appPhoneNumber = appPhoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
