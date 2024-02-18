package org.main.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.main.Flight;
import org.main.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CollectionFlightsDAOTest {

    private CollectionFlightsDAO collectionFlightsDAO;
    private File testFile;

    @BeforeEach
    void setUp() {
        testFile = new File("testFlightsData.bin");
        collectionFlightsDAO = new CollectionFlightsDAO(testFile);
    }

    @AfterAll
    static void afterAll() throws FileNotFoundException {
        File f = new File("testFlightsData.bin");
        f.delete();
    }

    @Test
    void testLoadAndSaveDataToFile() {
        Flight flight1 = new Flight(LocalDateTime.now(), "TestCity1", 1);
        Flight flight2 = new Flight(LocalDateTime.now(), "TestCity2", 2);

        collectionFlightsDAO.addFlightToBase(flight1);
        collectionFlightsDAO.addFlightToBase(flight2);

        collectionFlightsDAO.saveDataToFile();

        CollectionFlightsDAO newCollectionFlightsDAO = new CollectionFlightsDAO(testFile);

        newCollectionFlightsDAO.loadDataFromFile();

        List<Flight> loadedFlights = newCollectionFlightsDAO.getAllFlights();
        assertEquals(2, loadedFlights.size());
        assertTrue(loadedFlights.contains(flight1));
        assertTrue(loadedFlights.contains(flight2));
    }

    @Test
    void testGetFlightById() throws Exception {
        Flight flight = new Flight(LocalDateTime.now(), "TestCity", 123);
        collectionFlightsDAO.addFlightToBase(flight);

        Flight result = collectionFlightsDAO.getFlightById(123);

        assertNotNull(result);
        assertEquals(flight, result);
    }

    @Test
    void testGetFlightsByInfo() {
        Flight flight1 = new Flight(LocalDateTime.now(), "TestCity11111", 1);
        Flight flight2 = new Flight(LocalDateTime.now(), "TestCity3333", 2);

        collectionFlightsDAO.addFlightToBase(flight1);
        collectionFlightsDAO.addFlightToBase(flight2);

        LocalDate date = LocalDate.now();
        List<Flight> result = collectionFlightsDAO.getFlightsByInfo("TestCity3333", date, 1);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(flight2, result.get(0));
    }

    @Test
    void testAddPassengerToFlight() throws Exception {
        User passenger = new User("John", "Doe");
        Flight flight = new Flight(LocalDateTime.now(), "TestCity", 123);
        collectionFlightsDAO.addFlightToBase(flight);

        boolean result = collectionFlightsDAO.addPassengerToFlight(passenger, 123);

        assertTrue(result);
        assertTrue(flight.passengersOfFlight.contains(passenger));
    }
}
