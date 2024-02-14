package org.main.controllers;

import org.main.services.FlightService;

public class FlightController {

    FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
        displayAllFlights();
    }

    void displayAllFlights(){
        System.out.println("All flights");
    }
}
