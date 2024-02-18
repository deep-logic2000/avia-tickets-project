package org.main.controllers;

import org.main.Flight;
import org.main.User;
import org.main.services.FlightService;

import java.time.LocalDate;
import java.util.List;

public class FlightController {

    FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public void generateRandomFlights(){
        flightService.generateRandomFlights();
    };

    public void createNewRandomFlight(int flightNum, String destinationCity){
        flightService.createNewRandomFlight(flightNum, destinationCity);
    };

    List<Flight> getAllFlights() throws Exception{
        try{
            List<Flight> result = flightService.getAllFlights();;
            return result;

        } catch (Exception e) {
            System.out.println("Error. Try again.");
        }
        return null;
    };
    public void displayAllFlights(){
        flightService.displayAllFlights();
    }

    public void displayFlightByInfo(String destination, LocalDate dateOfFlight, int amountOfNecessaryTickets){
        List<Flight> filteredFlightsByInfo = getFlightByInfo(destination, dateOfFlight, amountOfNecessaryTickets);
           for (Flight flight : filteredFlightsByInfo){
               System.out.printf("%s\n", flight.prettyFormat());
           }
    }

    public Flight getFlightById(int id) throws Exception {
        return flightService.getFlightById(id);
    };

    public List<Flight> getFlightByInfo(String destination, LocalDate dateOfFlight, int amountOfNecessaryTickets){
        return flightService.getFlightByInfo(destination, dateOfFlight, amountOfNecessaryTickets);
    }

    public void displayFlightById(int id) throws Exception {
        try{
        Flight result = flightService.getFlightById(id);
        System.out.println(result.prettyFormat());

        } catch (Exception e) {
            System.out.println("There are no flights with such number. Try again.");
        }
    }

    public boolean addPassengerToFlight(User passenger, int flightId) throws Exception{
        return flightService.addPassengerToFlight(passenger, flightId);
    };

    public void saveFlightsDataToFile() {
        flightService.saveFlightsDataToFile();
    }

}
