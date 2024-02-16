package org.main.dao;

import org.main.Flight;
import org.main.User;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        Flight flight = this.flights.stream().filter(el -> el.flightNumber == id).findFirst().orElseThrow(() -> new Exception("There are no flights with such number. Try again."));
        return flight;
    }

    @Override
    public List<Flight> getFlightsByInfo(String destination, Date dateOfFlight, int amountOfNecessaryTickets){
        List<Flight> filteredFlights = this.flights.stream()
                .filter(el -> el.destinationCity == destination)
                .filter(el -> convertToLocalDateTimeToDate(el.dateAndTimeOfFlight) == dateOfFlight)
                .filter(el -> el.amountOfAvailablePlaces >= amountOfNecessaryTickets)
                .collect(Collectors.toList());

        return  filteredFlights;
    };

    public static Date convertToLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
