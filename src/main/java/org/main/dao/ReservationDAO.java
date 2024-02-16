package org.main.dao;

import org.main.Reservation;
import org.main.User;

import java.util.List;

public interface ReservationDAO {
List<Reservation> getAllReservationsOfThisUser(User user);
Reservation getReservationsOfThisUserById(User user, int id);
void addReservation(User user);
void deleteReservationById(User user, int id);

public boolean saveReservation(Reservation reservation);
}
