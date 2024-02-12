package org.main;

import org.main.controllers.UserController;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App implements Serializable {

    static UserController userController = new UserController();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        createNewUser();
        while (true) {
            showMainMenu();
        }

    }

    public static void showMainMenu() {
        System.out.print("Enter login: ");
        String login = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            boolean isAuthenticated = userController.authenticateUser(login, password);
            if (isAuthenticated) {
                System.out.println("Main menu");
            } else {
                System.out.println("Authentication failed. Please try again.");
            }
        } catch (BookingOverflowException e) {
            System.out.println(e.getMessage());
            System.out.println("Authentication failed. Please try again.");
        }
    }

    public static void createNewUser(){
        User user1 = new User("Pavlo", "Barabah");
        User user2 = new User("Kyrylo", "Nazarov");
        User user3 = new User("Yaroslav", "Voznyuk");
        userController.saveUser(user1);
        userController.saveUser(user2);
        userController.saveUser(user3);

    }
}
