package org.main;

import org.main.controllers.ReservationController;
import org.main.controllers.UserController;
import org.main.dao.CollectionFlightsDAO;
import org.main.services.FlightService;
import org.main.services.ReservationService;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */

public class Main {

//     static FlightService flightService = new FlightService(new CollectionFlightsDAO(new File("flight.bin")));
    public static void main(String[] args) {
//        List<Reservation> reservations = new ArrayList<>();
//        LocalDateTime localDateTime = LocalDateTime.of(2024, 10, 14,11,12);
//        Flight flight = new Flight(localDateTime, "Lviv", 12 );
//
//        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
//        User user2 = new User("Kyrylo", "Nazarov", "Кирило", "Назаров");
//
//        Reservation reservation1 = new Reservation(flight, user1, "Igor", "Melnik");
//        reservations.add(reservation1);
//        ReservationService reservationService = new ReservationService();
//        ReservationController reservationController = new ReservationController(reservationService);
//        reservationController.writingDataToAFile(reservations,"reservationDataFile.bin");
//        flightService.generateRandomFlights();
//        createNewUser();
        BookingApp bookingApp = new BookingApp();
        bookingApp.authenticateUser();
    }

    static UserController userController = new UserController();
    public static void createNewUser(){
        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        User user2 = new User("Kyrylo", "Nazarov", "Кирило", "Назаров");
        User user3 = new User("Yaroslav", "Voznyuk", "Ярослав", "Вознюк");
        userController.createNewUser(user1);
        userController.createNewUser(user2);
        userController.createNewUser(user3);

    }

//    private static final String FLIGHTS_DATA_FILE_NAME = "flights.bin";
//    public static void main(String[] args) throws Exception {
//
//        File file = new File(FLIGHTS_DATA_FILE_NAME);
//        CollectionFlightsDAO flightsDAO = new CollectionFlightsDAO(file);
//        FlightService flightService = new FlightService();
//        FlightController flightController = new FlightController(flightService);
//
//        if(flightsDAO.getAllFlights().size() < 1) flightService.generateRandomFlights();
//        flightController.displayAllFlights();
//        System.out.println("================================================");
//        flightController.displayFlightByInfo("City21", LocalDate.of(2024, 5, 23), 2); // MM/DD/YYYY
//        flightController.displayFlightById(15);
//    }
}
