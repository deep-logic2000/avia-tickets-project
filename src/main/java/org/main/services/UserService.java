package org.main.services;

import org.main.BookingOverflowException;
import org.main.User;
import org.main.dao.CollectionUserDao;

import java.io.File;
import java.util.List;

public class UserService {
    CollectionUserDao collectionUserDao = new CollectionUserDao(new File("Users.bin"));

    public List<User> getUserFromData(User user){
        return collectionUserDao.getUserFromData(user);
    }

    public boolean saveUser(User user){
        return collectionUserDao.saveUser(user);

    }

    public void createNewAccount(User user){
        User newUser = new User(user.getLogin(), user.getPassword());
        saveUser(newUser);
    }

    public boolean authenticateUser(String login, String password) {
        User user = new User(login, password);
        List<User> foundUsers = collectionUserDao.getUserFromData(user);
        return !foundUsers.isEmpty();
    }

}
