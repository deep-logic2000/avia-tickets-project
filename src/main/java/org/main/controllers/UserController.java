package org.main.controllers;

import org.main.User;
import org.main.services.UserService;

import java.util.List;

public class UserController {
    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public List<User> getUserFromData(User user){
        return userService.getUserFromData(user);
    }

    public boolean saveUser(User user){
        return userService.saveUser(user);
    }

    public void createNewUser(User user){
        userService.createNewAccount(user);
    }

    public boolean authenticateUser(String login, String password){
        return userService.authenticateUser(login, password);
    }
}
