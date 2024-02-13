package org.main.dao;

import org.main.Reservation;
import org.main.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CollectionsReservationDAO implements ReservationDAO {

    private List<Reservation> reservations = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

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
    public void addReservation(User user) {
        System.out.println("Input passenger name:");
        String passengerName = scanner.nextLine();
        System.out.println("Input passenger surname:");
        String passengerSurname = scanner.nextLine();
        System.out.println("Input flight number:");
        int flightNumber = scanner.nextInt();
        System.out.println("Input price:");
        double price = scanner.nextDouble();
        System.out.println("Input seat number:");
        int seatNumber = scanner.nextInt();
        Reservation reservation = new Reservation(passengerName, passengerSurname, flightNumber, user, price, seatNumber);

        reservations.add(reservation);
    }

    @Override
    public void deleteReservationById(User user, int id) {
        Reservation reservationDel = getAllReservationsOfThisUser(user).get(id);
        reservations.remove(reservationDel);
    }

}
