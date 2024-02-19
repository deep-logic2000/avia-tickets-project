package org.main;

import org.main.controllers.UserController;
import org.main.dao.CollectionFlightsDAO;
import org.main.services.FlightService;

import java.io.File;
import java.io.Serializable;

/**
 * Hello world!
 *
 */

public class Main {
    public static void main(String[] args) {
        BookingApp bookingApp = new BookingApp();
        bookingApp.authenticateUser();
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
