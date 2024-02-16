package org.main.dao;

import org.main.Flight;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface FlightsDAO {
    List<Flight> getAllFlights();
    Flight getFlightById(int id) throws Exception;
    List<Flight> getFlightsByInfo(String destination, LocalDate dateOfFlight, int amount);
    void loadDataFromFile();
    void saveDataToFile();
}
