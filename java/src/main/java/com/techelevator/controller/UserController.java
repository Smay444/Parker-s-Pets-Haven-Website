package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserDao userDao;

    private static final String API_BASE_PATH = "/users";

    @RequestMapping(path = API_BASE_PATH , method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userDao.getUsers();
    }
    @RequestMapping(path = API_BASE_PATH + "/adminlist", method = RequestMethod.GET)
    public List<User> getListOfAdmins(){
        return userDao.getAdminList();
    }
    @RequestMapping(path = API_BASE_PATH + "/promote" + "/{userId}", method = RequestMethod.PUT)
    public String promoteToAdmin(@PathVariable int userId){
        return userDao.makeUserAdmin(userId);
    }
    @RequestMapping(path = API_BASE_PATH + "/adopted" + "/{userId}", method = RequestMethod.GET)
    public User getUserByUserId(@PathVariable int userId){
        return userDao.getUserById(userId);
    }

}
