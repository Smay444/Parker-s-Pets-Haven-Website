package com.techelevator.dao;

import com.techelevator.model.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcApplicationsDao implements ApplicationsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserDao userDao;
    @Override
    public Applications getAppByAppId(int applicationId){
        Applications applications = null;
        String sql = "SELECT * \n" +
                "FROM applications\n" +
                "WHERE applicant_id = ?;";
        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,applicationId);
            if(rowSet.next()){
                applications = mapRowToApplication(rowSet);
            }

        } catch (Exception e) {
            System.out.println("Something went wrong getting aan Application by its ID");
        }
        return applications;
    }
    @Override
    public List<Applications> getAppsByUserId(int userId){
        List<Applications> applicationsList = new ArrayList<>();
        String sql = "SELECT * \n" +
                "FROM applications\n" +
                "WHERE user_id = ?;";
        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,userId);
            while(rowSet.next()){
                applicationsList.add(mapRowToApplication(rowSet));
            }

        } catch (Exception e){
            System.out.println("Something went wrong getting a list of Applications");
        }
        return applicationsList;
    }
    @Override
    public int createApplication(Applications applications){
        String sql = "INSERT INTO applications(user_id,app_email,app_name,app_phonenumber,application_date)\n" +
                "VALUES(?,?,?,?, NOW()) RETURNING applicant_id;";
        int applicationId = 0;
        List<Applications> applicationsList;
        applicationsList = getAppsByUserId(applications.getUserId());


        try{

            if (applicationsList.size() < 1) {

                applicationId = jdbcTemplate.queryForObject(sql, Integer.class, applications.getUserId(), applications.getAppEmail(), applications.getAppName(), applications.getAppPhoneNumber());
            }

        } catch (Exception e){
            System.out.println("Something went wrong creating an Application");
        }
        return applicationId;
    }
    @Override
    public Applications approve(int applicationId, String admin){
        Applications applications = null;
        String sql = "UPDATE applications\n" +
                "SET status = 'Approved', approved_by = ?\n" +
                "WHERE applicant_id = ?;";
        try{
            int rowsAffected;
            rowsAffected = jdbcTemplate.update(sql, admin, applicationId);
            if(rowsAffected == 0){
                throw new Exception("no rows affected");
            }

        } catch (Exception e) {
            System.out.println("Something went wrong approving an Application");
        }
        return applications;
    }
    @Override
    public Applications reject(int applicationId, String admin){
        Applications applications = null;
        String sql = "UPDATE applications\n" +
                "SET status = 'Rejected', approved_by = ?\n" +
                "WHERE applicant_id = ?;";
        try{
            int rowsAffected;
            rowsAffected = jdbcTemplate.update(sql, admin, applicationId);
            if(rowsAffected == 0){
                throw new Exception("no rows affected");
            }

        } catch (Exception e) {
            System.out.println("Something went wrong rejecting an Application");
        }
        return applications;
    }
    @Override
    public List<Applications> getAllVolunteers(){
        List<Applications> volunteers = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM applications\n";
        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            while(rowSet.next()){
                volunteers.add(mapRowToApplication(rowSet));
            }

        } catch (Exception e) {
            System.out.println("Something went wrong getting volunteers list");
        }
        return volunteers;
    }
    @Override
    public List<Applications> getAllApplications(){
        List<Applications> volunteers = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM applications\n";
        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            while(rowSet.next()){
                volunteers.add(mapRowToApplication(rowSet));
            }

        } catch (Exception e) {
            System.out.println("Something went wrong getting applications list");
        }
        return volunteers;
    }
    @Override
    public List<Applications> getAllPending(){
        List<Applications> applicationsList = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM applications\n" +
                "WHERE status = 'pending'";
        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            while(rowSet.next()){
                applicationsList.add(mapRowToApplication(rowSet));
            }

        } catch (Exception e) {
            System.out.println("Something went wrong getting volunteers list");
        }

        return applicationsList;
    }
    @Override
    public String promoteToAdmin(int userId){
        String promoted = "";
        String sql = "UPDATE users\n" +
                "SET role = 'ROLE_ADMIN'\n" +
                "WHERE user_id IN (\n" +
                "    SELECT user_id FROM applications\n" +
                "    WHERE status = 'Approved' and user_id = ?\n" +
                ");";
        try{
            int rowsAffected;
            rowsAffected = jdbcTemplate.update(sql, userId);
            if(rowsAffected == 0){
                throw new Exception("no rows affected");
            } else {
                promoted += "Volunteer has been promoted";
            }

        } catch (Exception e) {
            System.out.println("Something went wrong promoting a volunteer");
        }

        return promoted;
    }
    @Override
    public String promoteToVolunteer(int userId){
        String promoted = "";
        String sql = "UPDATE users\n" +
                "SET role = 'ROLE_VOLUNTEER'\n" +
                "WHERE user_id IN (\n" +
                "    SELECT user_id FROM applications\n" +
                "    WHERE status = 'Approved' and user_id = ?\n" +
                ");";
        try{
            int rowsAffected;
            rowsAffected = jdbcTemplate.update(sql, userId);
            if(rowsAffected == 0){
                throw new Exception("no rows affected");
            } else {
                promoted += "Volunteer has been promoted";
            }

        } catch (Exception e) {
            System.out.println("Something went wrong promoting a volunteer");
        }

        return promoted;

    }


    private Applications mapRowToApplication(SqlRowSet rs){
        Applications applications = new Applications();
        applications.setApplicationId(rs.getInt("applicant_id"));
        applications.setUserId(rs.getInt("user_id"));
        applications.setAppEmail(rs.getString("app_email"));
        applications.setAppName(rs.getString("app_name"));
        applications.setAppPhoneNumber(rs.getString("app_phonenumber"));
        applications.setStatus(rs.getString("status"));
        applications.setCreatedDate(rs.getTimestamp("application_date").toLocalDateTime());
        applications.setApprovedBy(rs.getString("approved_by"));
        return applications;
    }

}
