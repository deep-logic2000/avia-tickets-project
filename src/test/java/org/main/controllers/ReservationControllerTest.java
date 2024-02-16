package org.main.controllers;

import org.junit.jupiter.api.Test;
import org.main.Reservation;
import org.main.User;
import org.main.services.ReservationService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationControllerTest {
    public static final String TEST_FILE_NAME = "reservationDataFileFromTests.bin";
    @Test
    void getAllReservations() {
        ReservationService reservationService = new ReservationService();
        ReservationController reservationController = new ReservationController(reservationService);

        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        User user2 = new User("Kyrylo", "Nazarov", "Кирило", "Назаров");

        Reservation reservation1 = new Reservation(12, user1, 222, 112);
        Reservation reservation2 = new Reservation(13, user1, 232, 1123);
        Reservation reservation3 = new Reservation(14, user1, 213, 1233);
        Reservation reservation4 = new Reservation(15, user1, 241, 12);
        Reservation reservation5 = new Reservation(143, user2, 2222, 11122);

        reservationController.loadingDataFromAFile(TEST_FILE_NAME);

        List<Reservation> testReservations = new ArrayList<>();
        testReservations.add(reservation1);
        testReservations.add(reservation2);
        testReservations.add(reservation3);
        testReservations.add(reservation4);
        testReservations.add(reservation5);

        assertEquals(testReservations, reservationController.getAllReservations());
    }

    @Test
    void getAllReservationsOfThisUser() {
        ReservationService reservationService = new ReservationService();
        ReservationController reservationController = new ReservationController(reservationService);

        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        User user2 = new User("Kyrylo", "Nazarov", "Кирило", "Назаров");

        Reservation reservation1 = new Reservation(12, user1, 222, 112);
        Reservation reservation2 = new Reservation(13, user1, 232, 1123);
        Reservation reservation3 = new Reservation(14, user1, 213, 1233);
        Reservation reservation4 = new Reservation(15, user1, 241, 12);

        reservationController.loadingDataFromAFile(TEST_FILE_NAME);
        List<Reservation> testReservations = new ArrayList<>();
        testReservations.add(reservation1);
        testReservations.add(reservation2);
        testReservations.add(reservation3);
        testReservations.add(reservation4);


        assertEquals(testReservations, reservationController.getAllReservationsOfThisUser(user1));
    }

    @Test
    void getReservationsOfThisUserById() {
        ReservationService reservationService = new ReservationService();
        ReservationController reservationController = new ReservationController(reservationService);

        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");

        reservationController.loadingDataFromAFile(TEST_FILE_NAME);
        Reservation testReservations = new Reservation(12, user1, 222, 112);



        assertEquals(testReservations,reservationController.getReservationsOfThisUserById(user1,1));
    }

    @Test
    void deleteReservationById() {
        ReservationService reservationService = new ReservationService();
        ReservationController reservationController = new ReservationController(reservationService);


        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        reservationController.loadingDataFromAFile(TEST_FILE_NAME);

        reservationController.deleteReservationById(user1, 1);
        assertEquals(3, reservationController.getAllReservationsOfThisUser(user1).size());
    }

    @Test
    void saveReservation() {
        ReservationService reservationService = new ReservationService();
        ReservationController reservationController = new ReservationController(reservationService);

        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        reservationController.loadingDataFromAFile(TEST_FILE_NAME);

        Reservation reservation5 = new Reservation(143, user1, 2222, 11122);
        reservationController.saveReservation(reservation5);

        assertEquals(5, reservationController.getAllReservationsOfThisUser(user1).size());

    }


}