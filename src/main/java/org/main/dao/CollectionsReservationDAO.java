package org.main.dao;

import org.main.Flight;
import org.main.Reservation;
import org.main.User;
import org.main.services.FlightService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CollectionsReservationDAO implements ReservationDAO {

    private List<Reservation> reservations = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    public List<Reservation> getAllReservations() {
        return reservations;
    }

    @Override
    public List<Reservation> getAllReservationsOfThisUser(User user) {

        return reservations.stream()
                .filter(reservation -> reservation.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public Reservation getReservationsOfThisUserById(User user, int id) {

        return getAllReservationsOfThisUser(user).get(id - 1);
    }

    @Override
    public void addReservation(User user, Flight flight) {

        if(flight.amountOfAvailablePlaces > 0){
            System.out.println("Input Name:");
            String name = scanner.nextLine();
            System.out.println("Input Surname:");
            String surname = scanner.nextLine();
            Reservation reservation = new Reservation(flight, user, name,surname);
            flight.addPassenger(user);

            reservations.add(reservation);
        }else {
            System.out.println("now available seats");
        }

    }

    @Override
    public void deleteReservationById(User user, int id) {
        Reservation reservationDel = getAllReservationsOfThisUser(user).get(id);
        reservations.remove(reservationDel);
    }

    @Override
    public boolean saveReservation(Reservation reservation) {
        int reservationIndex = reservations.indexOf(reservation);
        if (reservationIndex != -1) {
            reservations.set(reservationIndex, reservation);
        } else {
            reservations.add(reservation);
        }
        return true;
    }


    public void loadData(List<Reservation> reservation) {
        reservations = reservation;
    }

    public void writingDataToAFile(List<Reservation> reservation, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(reservation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadingDataFromAFile(String fileName) {
        List<Reservation> reservations;
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            reservations = (List<Reservation>) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadData(reservations);
    }


}
