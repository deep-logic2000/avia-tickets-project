package org.main.controllers;

import org.main.Flight;
import org.main.services.FlightService;

import java.time.LocalDate;
import java.util.List;

public class FlightController {

    FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    public void displayAllFlights(){
        flightService.displayAllFlights();
    }

    public void displayFlightByInfo(String destination, LocalDate dateOfFlight, int amountOfNecessaryTickets){
        List<Flight> filteredFlightsByInfo = flightService.getFlightByInfo(destination, dateOfFlight, amountOfNecessaryTickets);
           for (Flight flight : filteredFlightsByInfo){
               System.out.printf("%s\n", flight.prettyFormat());
           }
    }

    public void displayFlightById(int id) throws Exception {
        Flight result = flightService.getFlightById(id);
        System.out.println(result.prettyFormat());
    }

}
