package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.techelevator.exception.DaoException;
import com.techelevator.model.RegisterUserDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT user_id, username, password_hash, name, role FROM users WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                user = mapRowToUser(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash, name, role FROM users";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                User user = mapRowToUser(results);
                users.add(user);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");
        User user = null;
        String sql = "SELECT user_id, username, password_hash, name, role FROM users WHERE username = ?;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
            if (rowSet.next()) {
                user = mapRowToUser(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public User createUser(RegisterUserDto user) {
        User newUser = null;
        String insertUserSql = "INSERT INTO users (username, password_hash, name, role) values (?, ?, ?, ?) RETURNING user_id";
        String password_hash = new BCryptPasswordEncoder().encode(user.getPassword());
        String ssRole = user.getRole().toUpperCase().startsWith("ROLE_") ? user.getRole().toUpperCase() : "ROLE_" + user.getRole().toUpperCase();
        try {
            int newUserId = jdbcTemplate.queryForObject(insertUserSql, int.class, user.getUsername(), password_hash, user.getName(), ssRole);
            newUser = getUserById(newUserId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newUser;
    }
    @Override
    public int getUserIdByName(String username){
        int userId = 0;
        String sql = "SELECT user_id\n" +
                "FROM users\n" +
                "WHERE username = ?;";
        try{
            userId = jdbcTemplate.queryForObject(sql, Integer.class, username);

        } catch (Exception e) {
            System.out.println("Something went wrong getting the user Id");
        }
        return userId;
    }
    @Override
    public String makeUserAdmin(int userId){
        String success = "";
        String sql = "update users\n" +
                "Set role = 'ROLE_ADMIN'\n" +
                "WHERE user_id = ?;";
        try{
            int rowsAffected;
            rowsAffected = jdbcTemplate.update(sql,userId);
            if(rowsAffected == 0){
                throw new Exception("no rows affected");
            }

        } catch (Exception e) {
            System.out.println("Something went wrong promoting a user to Admin");
        }
        return success;

    }
    @Override
    public List<User> getAdminList(){
        List<User> adminList = new ArrayList<>();
        String sql = "select *\n" +
                "from users\n" +
                "WHERE role = 'ROLE_ADMIN'";
        try{
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            while(rowSet.next()){
                adminList.add(mapRowToUser(rowSet));
            }

        } catch (Exception e) {
            System.out.println("Something went wrong getting the admin list");
        }
        return adminList;
    }
    @Override
    public String revokeAdminRole(int userId){
        String success = "Admin role has been revoked";
        String sql = "update users\n" +
                "Set role = 'ROLE_USER'\n" +
                "WHERE user_id = ?;";
        try{
            int rowsAffected;
            rowsAffected = jdbcTemplate.update(sql,userId);
            if(rowsAffected == 0){
                throw new Exception("no rows affected");
            }

        } catch (Exception e) {
            System.out.println("Something went wrong revoking a users admin role");
        }
        return success;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setName(rs.getString("name"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        return user;
    }
}
