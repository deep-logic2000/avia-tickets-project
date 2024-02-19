package org.main.dao;

import org.main.Flight;
import org.main.ResultNotFoundException;
import org.main.User;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectionFlightsDAO implements FlightsDAO {
    List<Flight> flights;
    private final File f;

    public CollectionFlightsDAO(File f) {
        this.flights = new ArrayList<Flight>();
        this.f = f;
        loadDataFromFile();
    }

    @Override
    public void loadDataFromFile() {
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                this.flights = (List<Flight>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(this.flights);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Flight> getAllFlights() {
        return this.flights;
    }

    @Override
    public Flight getFlightById(int id) throws Exception {
        Optional<Flight> flight = this.flights.stream().filter(el -> el.flightNumber == id).findFirst();
        if(flight.isPresent()){
            return flight.get();
        } else {
            throw new ResultNotFoundException("Result not found for ID: " + id);
        }
    }

    @Override
    public List<Flight> getFlightsByInfo(String destination, LocalDate dateOfFlight, int amountOfNecessaryTickets){
        List<Flight> filteredFlights = this.flights.stream()
                .filter(el -> el.destinationCity.toLowerCase().equals(destination.toLowerCase()))
                .filter(el -> compareDates(el.dateAndTimeOfFlight, dateOfFlight))
                .filter(el -> el.amountOfAvailablePlaces >= amountOfNecessaryTickets)
                .collect(Collectors.toList());
        return filteredFlights;
    }


    private boolean compareDates(LocalDateTime dateTimeOfFlight, LocalDate userInputedDate){
        boolean isDaysEquals = dateTimeOfFlight.getDayOfMonth() == userInputedDate.getDayOfMonth();
        boolean isMonthsEquals = dateTimeOfFlight.getMonth() == userInputedDate.getMonth();
        boolean isYearsEquals = dateTimeOfFlight.getYear() == userInputedDate.getYear();

        return isDaysEquals && isMonthsEquals && isYearsEquals;
    }

    public void addFlightToBase(Flight flight){
        this.flights.add(flight);
    }

    public boolean addPassengerToFlight(User passenger, int flightId) throws Exception{
        try {
            Flight flight = getFlightById(flightId);
            flight.addPassenger(passenger);
            return true;
        } catch (Exception e){
            System.out.println("Error add passenger! Try again.");
            return false;
        }
    };

    public void clearDataBase(){
        this.flights = new ArrayList<>();
    }

}
