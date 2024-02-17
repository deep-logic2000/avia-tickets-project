package org.main.services;

import org.main.Flight;
import org.main.User;
import org.main.dao.CollectionFlightsDAO;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FlightService implements Serializable {

    CollectionFlightsDAO flightsDAO;

    public FlightService() {
        this.flightsDAO = new CollectionFlightsDAO(new File("flights.bin"));
    }

    public void generateRandomFlights(){
        int countOfRandomFlights = (int) (20 + Math.random()*20);

        for (int i = 0; i < countOfRandomFlights; i++) {
            int randomNumberOfDestinationCity = (int) (1 + Math.random()*30);
            String destinationCity = "City" + randomNumberOfDestinationCity;
            Flight newFlight = createNewRandomFlight((i + 1), destinationCity);
            flightsDAO.addFlightToBase(newFlight);
        }
        flightsDAO.saveDataToFile();
    }

    Flight createNewRandomFlight(int flightNum, String destinationCity){

        int randomYear = randBetween(LocalDateTime.now().getYear(), 2025);
        int randomMonth = randBetween(LocalDateTime.now().getMonth().getValue(), 12);
        int randomDayOfMonth = randBetween(1, 28);
        int randomHour = randBetween(0, 23);
        int randomMinute = randBetween(0, 59);

        LocalDateTime randomDateTime = LocalDateTime.of(randomYear, randomMonth, randomDayOfMonth, randomHour, randomMinute);

        Flight flight = new Flight(randomDateTime, destinationCity, flightNum);

        int countOfPassengers = (int) (Math.random() * 5);
        for (int c = 0; c < countOfPassengers; c++) {
            String randomName = "Name" + c++;
            String randomSurname = "Surname" + c++;
            User randomPassenger = new User(randomName, randomSurname);
            flight.addPassenger(randomPassenger);
        }
        return flight;
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    List<Flight> getAllFlights(){
        List<Flight> allFlights = flightsDAO.getAllFlights();
        return allFlights;
    };

    public void displayAllFlights(){
        List<Flight> allFlights = getAllFlights();
//        int count = 1;
        if(allFlights.size() == 0){
            System.out.println("No flights available");
        }

        for (Flight flight : allFlights) {
            System.out.printf("%s\n", flight.prettyFormat());
        }
    }

    public Flight getFlightById(int id) throws Exception {
        return flightsDAO.getFlightById(id);
    }
    public List<Flight> getFlightByInfo(String destination, LocalDate dateOfFlight, int amountOfNecessaryTickets){
        return flightsDAO.getFlightsByInfo(destination, dateOfFlight, amountOfNecessaryTickets);
    }

    public boolean addPassengerToFlight(User passenger, int flightId) throws Exception{
        return flightsDAO.addPassengerToFlight(passenger, flightId);
    };

}
