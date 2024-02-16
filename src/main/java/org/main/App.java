package org.main;

import org.main.controllers.FlightController;
import org.main.dao.CollectionFlightsDAO;
import org.main.dao.FlightsDAO;
import org.main.services.FlightService;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Hello world!
 *
 */

public class App implements Serializable {
    private static final String FLIGHTS_DATA_FILE_NAME = "flights.bin";
    public static void main(String[] args) throws Exception {

        File file = new File(FLIGHTS_DATA_FILE_NAME);
        CollectionFlightsDAO flightsDAO = new CollectionFlightsDAO(file);
        FlightService flightService = new FlightService(flightsDAO);
        FlightController flightController = new FlightController(flightService);

        if(flightsDAO.getAllFlights().size() < 1) flightService.generateRandomFlights();
        flightController.displayAllFlights();
        System.out.println("================================================");
        flightController.displayFlightByInfo("City21", LocalDate.of(2024, 5, 23), 2); // MM/DD/YYYY
        flightController.displayFlightById(34);
    }
}
