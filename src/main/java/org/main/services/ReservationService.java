package org.main.services;


import org.main.BookingOverflowException;
import org.main.Flight;
import org.main.Reservation;
import org.main.User;
import org.main.dao.CollectionsReservationDAO;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;


public class ReservationService {
    private CollectionsReservationDAO reservations = new CollectionsReservationDAO();

    public List<Reservation> getAllReservations() {
        return reservations.getAllReservations();
    }

    public List<Reservation> getAllReservationsOfThisUser(User user) {
        return reservations.getAllReservationsOfThisUser(user);
    }

    public Reservation getReservationsOfThisUserById(User user, int id) {
        return reservations.getReservationsOfThisUserById(user, id);
    }

    public void addReservation(User user, Flight flight, String name, String surname) {
        reservations.addReservation(user, flight, name, surname);
    }

    public void deleteReservationById(User user, int id) {
        reservations.deleteReservationById(user, id);
    }

    public void displayAllReservationsOfThisUser(User user) {
        try {
            List<Reservation> allReservationsOfThisUser = getAllReservationsOfThisUser(user);
            if (!allReservationsOfThisUser.isEmpty()) {
                allReservationsOfThisUser.stream()
                        .forEachOrdered(reservation -> System.out.println("ID:" +
                                (allReservationsOfThisUser.indexOf(reservation) + 1)
                                + "\n" + reservation.prettyFormat()));

            } else {
                System.out.println("List of reservations is empty!");
            }
        } catch (RuntimeException e){
            throw new BookingOverflowException("Try again!");
        }
    }

    public void displayReservationsOfThisUserById(User user, int id){
        System.out.println(getReservationsOfThisUserById(user, id).prettyFormat());
    }

    public boolean saveReservation(Reservation reservation) {
        return reservations.saveReservation(reservation);
    }

    public void writingDataToAFile(List<Reservation> reservation, String fileName) {

        reservations.writingDataToAFile(reservation, fileName);
    }

    public void loadingDataFromAFile(String fileName) {

        reservations.loadingDataFromAFile(fileName);
    }

}
