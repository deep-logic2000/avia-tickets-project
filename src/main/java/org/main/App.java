package org.main;

import org.main.controllers.ReservationController;
import org.main.controllers.UserController;
import org.main.services.ReservationService;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App implements Serializable {

    static UserController userController = new UserController();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        createNewUser();
//        while (true) {
//            showMainMenu();
//        }
        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        User user2 = new User("Kyrylo", "Nazarov", "Кирило", "Назаров");
        User user3 = new User("Yaroslav", "Voznyuk", "Ярослав", "Вознюк");

        ReservationService reservationService = new ReservationService();
        ReservationController reservationController = new ReservationController(reservationService);

//        reservationController.addReservation(user1);
//        reservationController.addReservation(user1);
//        reservationController.addReservation(user1);
//        reservationController.addReservation(user2);
//        reservationController.addReservation(user2);
//        reservationController.addReservation(user3);
        reservationController.loadingDataFromAFile();

//        reservationController.writingDataToAFile(reservationController.getAllReservations());
        System.out.println("--------------------------displayAllReservationsOfThisUser-----------------------------");
        reservationController.displayAllReservationsOfThisUser(user1);
        reservationController.displayAllReservationsOfThisUser(user2);
        reservationController.displayAllReservationsOfThisUser(user3);
        reservationController.deleteReservationById(user1, 1);
        System.out.println("--------------------------displayAllReservationsOfThisUser-----------------------------");
        reservationController.displayAllReservationsOfThisUser(user1);
        System.out.println("--------------------------displayReservationsOfThisUserById-----------------------------");
        reservationController.displayReservationsOfThisUserById(user1, 1);


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

    public static void createNewUser() {
        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        User user2 = new User("Kyrylo", "Nazarov", "Кирило", "Назаров");
        User user3 = new User("Yaroslav", "Voznyuk", "Ярослав", "Вознюк");
        userController.createNewUser(user1);
        userController.createNewUser(user2);
        userController.createNewUser(user3);

    }
}
