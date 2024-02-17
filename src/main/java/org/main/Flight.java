package org.main;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Flight implements Serializable {
    int MAX_AMOUNT_OF_PASSENGERS = 150;
    public int flightNumber = 1;
    public LocalDateTime dateAndTimeOfFlight;
    public String destinationCity;
    public int amountOfAvailablePlaces;
    List<User> passengersOfFlight;

    public Flight(LocalDateTime dateAndTimeOfFlight, String destinationCity, int amountOfAvailablePlaces, int flightNumber) {
        this.flightNumber = flightNumber;
        this.dateAndTimeOfFlight = dateAndTimeOfFlight;
        this.destinationCity = destinationCity;
        this.amountOfAvailablePlaces = amountOfAvailablePlaces;
        this.passengersOfFlight = new ArrayList<User>();
    };

    public Flight(LocalDateTime dateAndTimeOfFlight, String destinationCity, int flightNumber) {
        this.flightNumber = flightNumber;
        this.dateAndTimeOfFlight = dateAndTimeOfFlight;
        this.destinationCity = destinationCity;
        this.amountOfAvailablePlaces = MAX_AMOUNT_OF_PASSENGERS;
        this.passengersOfFlight = new ArrayList<User>();
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
        StringBuilder result = new StringBuilder();
        result.append("Flight #").append(flightNumber + ":\t");
        result.append("Departure: ").append(addZeros(String.valueOf(this.dateAndTimeOfFlight.getDayOfMonth())) + "/" + addZeros(String.valueOf(this.dateAndTimeOfFlight.getMonthValue())) + "/" + addZeros(String.valueOf(this.dateAndTimeOfFlight.getYear())) + "\t");
        result.append(addZeros(String.valueOf(this.dateAndTimeOfFlight.getHour())) + ":" + addZeros(String.valueOf(this.dateAndTimeOfFlight.getMinute())) + "\t");
        result.append("Destination: ").append(this.destinationCity + "\t");
        result.append("Available tickets amount: ").append(this.amountOfAvailablePlaces);

        return result.toString();
    }

    String addZeros(String input){
        int zerosToAdd = 2 - input.length();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < zerosToAdd; i++) {
            result.append("0");
        }
        result.append(input);

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return MAX_AMOUNT_OF_PASSENGERS == flight.MAX_AMOUNT_OF_PASSENGERS && flightNumber == flight.flightNumber && amountOfAvailablePlaces == flight.amountOfAvailablePlaces && Objects.equals(dateAndTimeOfFlight, flight.dateAndTimeOfFlight) && Objects.equals(destinationCity, flight.destinationCity) && Objects.equals(passengersOfFlight, flight.passengersOfFlight);
//        return Objects.equals()
    }

    @Override
    public int hashCode() {
        return Objects.hash(MAX_AMOUNT_OF_PASSENGERS, flightNumber, dateAndTimeOfFlight, destinationCity, amountOfAvailablePlaces, passengersOfFlight);
    }
}
