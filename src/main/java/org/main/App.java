package org.main;

import org.main.controllers.ReservationController;
import org.main.controllers.UserController;
import org.main.services.ReservationService;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App implements Serializable {

    public static final String FILE_NAME = "reservationDataFile.bin";
    public static final String TEST_FILE_NAME = "reservationDataFileFromTests.bin";
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

        LocalDateTime localDateTime = LocalDateTime.of(2024, 10, 14,11,12);

        Flight flight = new Flight(localDateTime, "Lviv", 12 );

        Reservation reservation1 = new Reservation(flight, user1, 222, 112);
        Reservation reservation2 = new Reservation(flight, user1, 232, 1123);
        Reservation reservation3 = new Reservation(flight, user1, 213, 1233);
        Reservation reservation4 = new Reservation(flight, user1, 241, 12);
        Reservation reservation5 = new Reservation(flight, user2, 2222, 11122);

        reservationController.saveReservation(reservation1);
        reservationController.saveReservation(reservation2);
        reservationController.saveReservation(reservation3);
        reservationController.saveReservation(reservation4);
        reservationController.saveReservation(reservation5);
//        reservationController.addReservation(user1);
//        reservationController.addReservation(user1);
//        reservationController.addReservation(user1);
//        reservationController.addReservation(user2);
//        reservationController.addReservation(user2);
//        reservationController.addReservation(user3);


//        reservationController.loadingDataFromAFile(FILE_NAME);


        reservationController.writingDataToAFile(reservationController.getAllReservations(), (TEST_FILE_NAME));

        System.out.println("--------------------------displayAllReservationsOfThisUser1-----------------------------");
        reservationController.displayAllReservationsOfThisUser(user1);
        System.out.println("--------------------------displayAllReservationsOfThisUser2-----------------------------");
        reservationController.displayAllReservationsOfThisUser(user2);
        System.out.println("--------------------------displayAllReservationsOfThisUser3-----------------------------");
        reservationController.displayAllReservationsOfThisUser(user3);
        reservationController.deleteReservationById(user1, 1);
        System.out.println("--------------------------displayAllReservationsOfThisUser1-----------------------------");
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
