package org.main.dao;

import org.main.User;

import java.util.List;

public interface UserDAO {
    User getUserFromData(String login, String password);
    boolean saveUser(User user);

}
