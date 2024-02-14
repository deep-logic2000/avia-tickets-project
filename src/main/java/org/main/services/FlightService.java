package org.main.services;

import org.main.Flight;
import org.main.User;
import org.main.dao.CollectionFlightsDAO;

import java.time.LocalDateTime;

public class FlightService {

    CollectionFlightsDAO flightsDAO;

    public FlightService(CollectionFlightsDAO flightsDAO) {
        this.flightsDAO = flightsDAO;
    }

    void generateRandomFlights(){
        int countOfRandomFlights = (int) (20 + Math.random()*20);
        System.out.println("Test1: countOfRandomFlights - " + countOfRandomFlights);
        for (int i = 0; i < countOfRandomFlights; i++) {

            int countOfPassengers = (int) (Math.random() * 5);

            Flight flight = new Flight(LocalDateTime.now(), "City" + i, i);

            for (int c = 0; c < countOfPassengers; c++) {
                String randomName = "Name" + c++;
                String randomSurname = "Surname" + c++;
                User randomPassenger = new User(randomName, randomSurname);
                flight.addPassenger(randomPassenger);
            }
        }
    }

    Flight createNewFlight(){

    }
}
