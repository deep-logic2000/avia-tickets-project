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

        if (!filteredFlightsByInfo.isEmpty()) {
            for (Flight flight : filteredFlightsByInfo) {
                System.out.printf("%s\n", flight.prettyFormat());
            }
        } else {
            System.out.println("There are no flights with such parameters. Booking is not aviable");
        }
    }

    public void displayFlightById(int id) throws Exception {
        try{
        Flight result = flightService.getFlightById(id);
        System.out.println(result.prettyFormat());

        } catch (Exception e) {
            System.out.println("There are no flights with such number. Try again.");
        }
    }

    public List<Flight> getFlightByInfo(String destination, LocalDate dateOfFlight, int amountOfNecessaryTickets){
        return flightService.getFlightByInfo(destination, dateOfFlight, amountOfNecessaryTickets);
    }

}
