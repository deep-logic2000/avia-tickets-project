package org.main.controllers;

import org.main.Reservation;
import org.main.User;
import org.main.services.ReservationService;

import java.util.List;

public class ReservationController {
    private ReservationService reservationService;


    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public List<Reservation> getAllReservationsOfThisUser(User user) {
        return reservationService.getAllReservationsOfThisUser(user);
    }

    public Reservation getReservationsOfThisUserById(User user, int id) {
        return reservationService.getReservationsOfThisUserById(user, id);
    }

    public void addReservation(User user) {
        reservationService.addReservation(user);
    }

    public void deleteReservationById(User user, int id) {
        reservationService.deleteReservationById(user, id);
    }

    public void displayAllReservationsOfThisUser(User user) {
        reservationService.displayAllReservationsOfThisUser(user);
    }

    public void displayReservationsOfThisUserById(User user, int id){
        System.out.println(getReservationsOfThisUserById(user, id).prettyFormat());
    }
}
