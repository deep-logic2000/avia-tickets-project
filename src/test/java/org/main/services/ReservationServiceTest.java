package org.main.services;

import org.junit.jupiter.api.Test;
import org.main.Flight;
import org.main.Reservation;
import org.main.User;
import org.main.dao.CollectionsReservationDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    public static final String TEST_FILE_NAME = "reservationDataFileFromTests.bin";

    @Test
    void getAllReservations() {
        ReservationService reservationService = new ReservationService();

        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        User user2 = new User("Kyrylo", "Nazarov", "Кирило", "Назаров");

        LocalDateTime localDateTime = LocalDateTime.of(2024, 10, 14,11,12);
        Flight flight = new Flight(localDateTime, "Lviv", 12 );

        Reservation reservation1 = new Reservation(flight, user1, "Igor", "Melnik");
        Reservation reservation2 = new Reservation(flight, user1, "Marina", "Ivanova");
        Reservation reservation3 = new Reservation(flight, user1, "Oleh", "Ivanov");
        Reservation reservation4 = new Reservation(flight, user1, "Ivan", "Ivanov");
        Reservation reservation5 = new Reservation(flight, user2, "Sasha", "Ivanov");

        reservationService.loadingDataFromAFile(TEST_FILE_NAME);

        List<Reservation> testReservations = new ArrayList<>();
        testReservations.add(reservation1);
        testReservations.add(reservation2);
        testReservations.add(reservation3);
        testReservations.add(reservation4);
        testReservations.add(reservation5);

        assertEquals(testReservations, reservationService.getAllReservations());
    }

    @Test
    void getAllReservationsOfThisUser() {
        ReservationService reservationService = new ReservationService();

        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        User user2 = new User("Kyrylo", "Nazarov", "Кирило", "Назаров");

        LocalDateTime localDateTime = LocalDateTime.of(2024, 10, 14,11,12);
        Flight flight = new Flight(localDateTime, "Lviv", 12 );

        Reservation reservation1 = new Reservation(flight, user1, "Igor", "Melnik");
        Reservation reservation2 = new Reservation(flight, user1, "Marina", "Ivanova");
        Reservation reservation3 = new Reservation(flight, user1, "Oleh", "Ivanov");
        Reservation reservation4 = new Reservation(flight, user1, "Ivan", "Ivanov");

        reservationService.loadingDataFromAFile(TEST_FILE_NAME);
        List<Reservation> testReservations = new ArrayList<>();
        testReservations.add(reservation1);
        testReservations.add(reservation2);
        testReservations.add(reservation3);
        testReservations.add(reservation4);


        assertEquals(testReservations, reservationService.getAllReservationsOfThisUser(user1));
    }

    @Test
    void getReservationsOfThisUserById() {
        ReservationService reservationService = new ReservationService();

        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");

        LocalDateTime localDateTime = LocalDateTime.of(2024, 10, 14,11,12);
        Flight flight = new Flight(localDateTime, "Lviv", 12 );

        reservationService.loadingDataFromAFile(TEST_FILE_NAME);
        Reservation testReservations = new Reservation(flight, user1, "Igor", "Melnik");



        assertEquals(testReservations, reservationService.getReservationsOfThisUserById(user1,1));
    }


    @Test
    void deleteReservationById() {
        ReservationService reservationService = new ReservationService();

        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        reservationService.loadingDataFromAFile(TEST_FILE_NAME);

        reservationService.deleteReservationById(user1, 1);
        assertEquals(3, reservationService.getAllReservationsOfThisUser(user1).size());
    }

    @Test
    void saveReservation() {
        ReservationService reservationService = new ReservationService();

        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        reservationService.loadingDataFromAFile(TEST_FILE_NAME);

        LocalDateTime localDateTime = LocalDateTime.of(2024, 10, 14,11,12);
        Flight flight = new Flight(localDateTime, "Lviv", 12 );

        Reservation reservation5 = new Reservation(flight, user1, "Sasha", "Ivanov");
        reservationService.saveReservation(reservation5);

        assertEquals(5, reservationService.getAllReservationsOfThisUser(user1).size());

    }

}