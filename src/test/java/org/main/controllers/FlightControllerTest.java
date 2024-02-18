package org.main.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.main.Flight;
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

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.file));
        oos.writeObject(this.flights);
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

}