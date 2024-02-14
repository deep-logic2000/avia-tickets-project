package org.main;

import org.main.controllers.FlightController;
import org.main.dao.CollectionFlightsDAO;
import org.main.dao.FlightsDAO;
import org.main.services.FlightService;

import java.io.File;

/**
 * Hello world!
 *
 */

public class App {
    private static final String FLIGHTS_DATA_FILE_NAME = "flights.dat";
    public static void main(String[] args) {
                System.out.println("Hello World!!!!");
        System.out.println("Hello Friends!!!!");

        File file = new File(FLIGHTS_DATA_FILE_NAME);
        CollectionFlightsDAO flightsDAO = new CollectionFlightsDAO(file);
        FlightService flightService = new FlightService(flightsDAO);
        FlightController flightController = new FlightController(flightService);

    }
}
