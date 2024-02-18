package org.main.controllers;

import org.main.User;
import org.main.services.UserService;

import java.util.List;

public class UserController {
    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public User getUserFromData(String login, String password){
        return userService.getUserFromData(login, password);
    }

    public boolean saveUser(User user){
        return userService.saveUser(user);
    }

    public void createNewUser(User user){
        userService.createNewAccount(user);
    }

    public User authenticateUser(String login, String password){
        return userService.authenticateUser(login, password);
    }
}
