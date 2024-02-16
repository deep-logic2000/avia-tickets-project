package org.main.dao;

import org.main.Flight;
import org.main.Reservation;
import org.main.User;

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
        System.out.println("Input price:");
        double price = scanner.nextDouble();
        System.out.println("Input seat number:");
        int seatNumber = scanner.nextInt();
        Reservation reservation = new Reservation(flight, user, price, seatNumber);

        reservations.add(reservation);
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
