package org.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Flight {
    int MAX_AMOUNT_OF_PASSENGERS = 150;
    public int flightNumber = 0;
    public LocalDateTime dateAndTimeOfFlight;
    public String destinationCity;
    public int amountOfAvailablePlaces;
    List<User> passengersOfFlight;

    public Flight(LocalDateTime dateAndTimeOfFlight, String destinationCity, int amountOfAvailablePlaces, int flightNumber) {
        this.flightNumber = flightNumber;
        this.dateAndTimeOfFlight = dateAndTimeOfFlight;
        this.destinationCity = destinationCity;
        this.amountOfAvailablePlaces = amountOfAvailablePlaces;
    };

    public Flight(LocalDateTime dateAndTimeOfFlight, String destinationCity, int flightNumber) {
        this.flightNumber = flightNumber;
        this.dateAndTimeOfFlight = dateAndTimeOfFlight;
        this.destinationCity = destinationCity;
        this.amountOfAvailablePlaces = MAX_AMOUNT_OF_PASSENGERS;
    };

    int getAmountOfAvailablePlaces(){
        return amountOfAvailablePlaces;
    };

    void setAmountOfAvailablePlaces(int newAmount){
        this.amountOfAvailablePlaces = newAmount;
    };

    public void addPassenger(User passenger){
        passengersOfFlight.add(passenger);
        decreaseAmountOfAvailablePlaces();
    };

    List<User> getPassengersOfFlight(){
      return passengersOfFlight;
    };

    void decreaseAmountOfAvailablePlaces(){
        setAmountOfAvailablePlaces(this.amountOfAvailablePlaces - 1);
    }

        public String prettyFormat() {
        StringBuilder result = new StringBuilder("Flight #" + flightNumber + "\n");

        result.append("Departure: ").append(this.dateAndTimeOfFlight.getHour() + " : " + this.dateAndTimeOfFlight.getMinute());
        result.append("Destination: ").append(this.destinationCity);
        result.append("Available tickets: ").append(this.amountOfAvailablePlaces);

        return result.toString();
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber=" + flightNumber +
                ", destinationCity='" + destinationCity + '\'' +
                ", dateAndTimeOfFlight=" + dateAndTimeOfFlight +
                ", amountOfAvailablePlaces=" + amountOfAvailablePlaces +
                '}';
    }
}
