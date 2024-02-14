package org.main.dao;

import org.main.Flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightsDAO {
    List<Flight> getAllFlights();
    Flight getFlightById(int id);
    List<Flight> getFlightsByInfo(String destination, LocalDate dateOfFlight, int amount);
    void loadFlightsData();
    void saveFlight(Flight flight);
}
