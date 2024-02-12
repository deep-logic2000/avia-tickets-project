package org.main.dao;

import org.main.Reservation;
import org.main.User;

import java.util.ArrayList;
import java.util.List;

public class CollectionsReservationDAO implements ReservationDAO{

    private List<Reservation> reservations = new ArrayList<>();
    @Override
    public List<Reservation> getAllReservationsOfThisUser(User user) {
        return null;
    }

    @Override
    public Reservation getReservationsOfThisUserById(User user, int id) {
        return null;
    }

    @Override
    public void addReservation() {

    }

    @Override
    public void deleteReservationById(User user, int id) {

    }
}
