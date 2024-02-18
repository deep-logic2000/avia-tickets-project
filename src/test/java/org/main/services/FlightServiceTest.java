package org.main.services;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.main.Flight;
import org.main.User;
import org.main.dao.CollectionFlightsDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class FlightServiceTest {
    public static CollectionFlightsDAO flightsDAO;
    public static FlightService flightService;
    public static File testFile;

    @BeforeClass
    public static void setUp() {
        testFile = new File("testFlightsData.bin");
        flightsDAO = new CollectionFlightsDAO(testFile);
        flightService = new FlightService(flightsDAO);
    }

    @AfterAll
    static void afterAll() throws FileNotFoundException {
        File f = new File("testFlightsData.bin");
        f.delete();
    }
    @Test
    public void generateRandomFlights() {
        flightService.generateRandomFlights();

        List<Flight> resultFlights = flightsDAO.getAllFlights();

        assertNotNull(resultFlights);
        assertTrue(resultFlights.size() > 0);
    }

    @Test
    public void testCreateNewRandomFlight() {
            int flightNum = 123;
            String destinationCity = "New York";

            Flight result = flightService.createNewRandomFlight(flightNum, destinationCity);

            assertNotNull(result);
            assertEquals(flightNum, result.flightNumber);
            assertEquals(destinationCity, result.destinationCity);

            LocalDateTime now = LocalDateTime.now();
            assertTrue(result.dateAndTimeOfFlight.isAfter(now));
            assertTrue(result.dateAndTimeOfFlight.isBefore(now.plusYears(5)));

            assertTrue(result.passengersOfFlight.size() > 0);
    };

    @Test
    public void getAllFlights() {
        File f = new File("testFlightsData.bin");
        f.delete();
        flightsDAO.clearDataBase();

        Flight flight1 = new Flight(LocalDateTime.of(2024, 02, 17, 12, 00), "City1", 1);
        Flight flight2 = new Flight(LocalDateTime.of(2024, 02, 17, 12, 05), "City2", 2);

        List<Flight> mockFlights = List.of(flight1, flight2);
        flightsDAO.addFlightToBase(flight1);
        flightsDAO.addFlightToBase(flight2);

        List<Flight> result = flightService.getAllFlights();

        assertNotNull(result);
        assertEquals(mockFlights, result);
    }

    @Test
    public void testGetFlightById() throws Exception {
        int flightId = 123;
        Flight mockFlight = new Flight(LocalDateTime.now(), "TestCity", flightId);
        flightsDAO.addFlightToBase(mockFlight);

        Flight result = flightService.getFlightById(flightId);

        assertNotNull(result);
        assertEquals(mockFlight, result);
    }

    @Test
    public void testAddPassengerToFlight() throws Exception {
        User passenger = new User("John", "Doe");
        int flightId = 456;
        Flight mockFlight = new Flight(LocalDateTime.now(), "TestCity", flightId);
        flightsDAO.addFlightToBase(mockFlight);

        boolean result = flightService.addPassengerToFlight(passenger, flightId);

        assertTrue(result);
        assertTrue(mockFlight.passengersOfFlight.contains(passenger));
    }

    @Test
    public void testSaveFlightsDataToFile() {
        List<Flight> mockFlights = List.of(new Flight(LocalDateTime.now(), "City1", 1),
                new Flight(LocalDateTime.now(), "City2", 2));
        flightsDAO.addFlightToBase(mockFlights.get(0));
        flightsDAO.addFlightToBase(mockFlights.get(1));

        flightService.saveFlightsDataToFile();

        flightsDAO.loadDataFromFile();

        List<Flight> resultFlights = flightsDAO.getAllFlights();

        assertNotNull(resultFlights);
        assertEquals(mockFlights, resultFlights);
    }
}