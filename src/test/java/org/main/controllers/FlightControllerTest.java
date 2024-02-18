package org.main.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.main.Flight;
import org.main.User;
import org.main.dao.CollectionFlightsDAO;
import org.main.services.FlightService;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FlightControllerTest {
    File file;
    CollectionFlightsDAO flightsDAO;
    FlightService flightService;
    FlightController flightController;
    List<Flight> flights;
    Flight flight1;
    Flight flight2;
    @Before
    public void setUp() throws Exception {
        this.file = new File("testFlights.bin");
        this.flightsDAO = new CollectionFlightsDAO(this.file);
        this.flightService = new FlightService(this.flightsDAO);
        this.flightController = new FlightController(flightService);
        this.flights = new ArrayList<Flight>();
        flight1 = new Flight(LocalDateTime.of(2024, 01, 01, 12, 10), "Viena", 1);
        flight2 = new Flight(LocalDateTime.of(2025, 02, 02, 13, 11), "Oslo", 2);
        this.flights.add(flight1);
        this.flights.add(flight2);
        flightsDAO.addFlightToBase(flight1);
        flightsDAO.addFlightToBase(flight2);
        flightsDAO.saveDataToFile();
    }

    @AfterAll
    static void afterAll() throws FileNotFoundException {
        File f = new File("testFlights.bin");
        f.delete();
    }

    @Test
    public void generateRandomFlights() {
        flightController.generateRandomFlights();

        List<Flight> resultFlights = flightsDAO.getAllFlights();

        assertNotNull(resultFlights);
        assertTrue(resultFlights.size() > 0);
    }

    @Test
    public void testCreateNewRandomFlight() throws Exception {
        flightsDAO.clearDataBase();
        int flightNum = 777;
        String destinationCity = "NewCity";

        Flight newFlight = flightService.createNewRandomFlight(flightNum, destinationCity);
        flightsDAO.addFlightToBase(newFlight);
        Flight result = this.flightsDAO.getFlightById(flightNum);

        assertNotNull(result);
        assertEquals(flightNum, result.flightNumber);
        assertEquals(destinationCity, result.destinationCity);

        LocalDateTime now = LocalDateTime.now();
        assertTrue(result.dateAndTimeOfFlight.isAfter(now));
        assertTrue(result.dateAndTimeOfFlight.isBefore(now.plusYears(5)));

        assertTrue(result.passengersOfFlight.size() >= 0);
    };

    @Test
    public void getAllFlights() throws Exception {
        File f = new File("testFlightsData.bin");
        f.delete();
        flightsDAO.clearDataBase();

        Flight flight1 = new Flight(LocalDateTime.of(2024, 02, 17, 12, 00), "City1", 1);
        Flight flight2 = new Flight(LocalDateTime.of(2024, 02, 17, 12, 05), "City2", 2);

        List<Flight> mockFlights = List.of(flight1, flight2);
        flightsDAO.addFlightToBase(flight1);
        flightsDAO.addFlightToBase(flight2);

        List<Flight> result = flightController.getAllFlights();

        assertNotNull(result);
        assertEquals(mockFlights, result);
    }
    @Test
    public void testDisplayFlightByInfo() {
        String filteredFlight = flightService.getFlightByInfo("Oslo", LocalDate.of(2025, 02, 02), 1).get(0).prettyFormat();

        String expectedOutput  = "Flight #2:	Departure: 02/02/2025	13:11	Destination: Oslo	Available tickets amount: 150";
        assertEquals(expectedOutput, filteredFlight);
    }

    @Test
    public void testDisplayFlightById() throws Exception {
        String filteredFlight = flightService.getFlightById(2).prettyFormat();

        String expectedOutput  = "Flight #2:	Departure: 02/02/2025	13:11	Destination: Oslo	Available tickets amount: 150";
        assertEquals(expectedOutput, filteredFlight);
    }

    @Test
    public void testGetFlightByInfo() {
        String destination = "TestCity";
        LocalDate dateOfFlight = LocalDate.now();
        int amountOfNecessaryTickets = 2;
        List<Flight> expectedFlights = flightsDAO.getFlightsByInfo(destination, dateOfFlight, amountOfNecessaryTickets);
        List<Flight> result = flightController.getFlightByInfo(destination, dateOfFlight, amountOfNecessaryTickets);

        assertEquals(expectedFlights, result);
    }

    @Test
    public void testAddPassengerToFlight() throws Exception {
        User passenger = new User("John", "Doe");
        int flightId = 1;
        boolean result = flightController.addPassengerToFlight(passenger, flightId);

        assertTrue(result);
    }

    @Test
    public void testSaveFlightsDataToFile() {
        flightsDAO.clearDataBase();
        List<Flight> mockFlights = List.of(flight1, flight2);
        flightsDAO.addFlightToBase(mockFlights.get(0));
        flightsDAO.addFlightToBase(mockFlights.get(1));

        flightService.saveFlightsDataToFile();

        flightsDAO.loadDataFromFile();

        List<Flight> resultFlights = flightsDAO.getAllFlights();

        assertNotNull(resultFlights);
        assertEquals(mockFlights, resultFlights);
    }
}