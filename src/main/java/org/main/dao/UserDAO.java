package org.main.dao;

import org.main.User;

import java.util.List;

public interface UserDAO {
    List<User> getUserFromData(User user);
    boolean saveUser(User user);

}
