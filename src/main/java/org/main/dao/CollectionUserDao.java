package org.main.dao;

import org.main.BookingOverflowException;
import org.main.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectionUserDao implements UserDAO {
    private List<User> databaseOfUsers;
    private final File f;

    public CollectionUserDao(File f) {
        this.f = f;
        this.databaseOfUsers = new ArrayList<>();
        loadDataFromFile();
    }

    @Override
    public User getUserFromData(String login, String password) {
        Optional<User> optionalUser = databaseOfUsers.stream()
                .filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password))
                .findFirst();

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new BookingOverflowException("Користувач не знайдений!");
        }
    }


    @Override
    public boolean saveUser(User user) {
        int existingIndex = databaseOfUsers.indexOf(user);
        if (existingIndex != -1) {
            databaseOfUsers.set(existingIndex, user);
        } else {
            databaseOfUsers.add(user);
        }
        saveDataToFile();
        return true;
    }

    private void loadDataFromFile() {
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                databaseOfUsers = (List<User>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(databaseOfUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
