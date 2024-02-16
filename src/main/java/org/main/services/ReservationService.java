package org.main.services;


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

    public void addReservation(User user) {
        reservations.addReservation(user);
    }

    public void deleteReservationById(User user, int id) {
        reservations.deleteReservationById(user, id);
    }

    public void displayAllReservationsOfThisUser(User user) {
        List<Reservation> allReservationsOfThisUser = getAllReservationsOfThisUser(user);
        if (allReservationsOfThisUser != null) {
            allReservationsOfThisUser.stream()
                    .forEachOrdered(reservation -> System.out.println(allReservationsOfThisUser.indexOf(reservation) + 1
                    + " " + reservation.prettyFormat()));

        } else {
            System.out.println("Список бронювань пустий!!!");
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
