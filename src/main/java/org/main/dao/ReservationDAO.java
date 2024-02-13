package org.main.dao;

import org.main.Reservation;
import org.main.User;

import java.util.List;

public interface ReservationDAO {
List<Reservation> getAllReservationsOfThisUser(User user);
Reservation getReservationsOfThisUserById(User user, int id);
void addReservation();
void deleteReservationById(User user, int id);
}
