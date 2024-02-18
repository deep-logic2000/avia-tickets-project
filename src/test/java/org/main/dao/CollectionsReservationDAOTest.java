package org.main.dao;

import org.junit.jupiter.api.Test;
import org.main.Flight;
import org.main.Reservation;
import org.main.User;
import org.main.controllers.ReservationController;
import org.main.services.ReservationService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsReservationDAOTest {

    public static final String TEST_FILE_NAME = "reservationDataFileFromTests.bin";

    @Test
    void getAllReservations() {
        CollectionsReservationDAO collectionsReservationDAO = new CollectionsReservationDAO();

        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        User user2 = new User("Kyrylo", "Nazarov", "Кирило", "Назаров");

        LocalDateTime localDateTime = LocalDateTime.of(2024, 10, 14,11,12);
        Flight flight = new Flight(localDateTime, "Lviv", 12 );

        Reservation reservation1 = new Reservation(flight, user1, "Igor", "Melnik");
        Reservation reservation2 = new Reservation(flight, user1, "Marina", "Ivanova");
        Reservation reservation3 = new Reservation(flight, user1, "Oleh", "Ivanov");
        Reservation reservation4 = new Reservation(flight, user1, "Ivan", "Ivanov");
        Reservation reservation5 = new Reservation(flight, user2, "Sasha", "Ivanov");

        collectionsReservationDAO.loadingDataFromAFile(TEST_FILE_NAME);

        List<Reservation> testReservations = new ArrayList<>();
        testReservations.add(reservation1);
        testReservations.add(reservation2);
        testReservations.add(reservation3);
        testReservations.add(reservation4);
        testReservations.add(reservation5);

        assertEquals(testReservations, collectionsReservationDAO.getAllReservations());
    }

    @Test
    void getAllReservationsOfThisUser() {
        CollectionsReservationDAO collectionsReservationDAO = new CollectionsReservationDAO();

        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        User user2 = new User("Kyrylo", "Nazarov", "Кирило", "Назаров");

        LocalDateTime localDateTime = LocalDateTime.of(2024, 10, 14,11,12);
        Flight flight = new Flight(localDateTime, "Lviv", 12 );

        Reservation reservation1 = new Reservation(flight, user1, "Igor", "Melnik");
        Reservation reservation2 = new Reservation(flight, user1, "Marina", "Ivanova");
        Reservation reservation3 = new Reservation(flight, user1, "Oleh", "Ivanov");
        Reservation reservation4 = new Reservation(flight, user1, "Ivan", "Ivanov");


        collectionsReservationDAO.loadingDataFromAFile(TEST_FILE_NAME);
        List<Reservation> testReservations = new ArrayList<>();
        testReservations.add(reservation1);
        testReservations.add(reservation2);
        testReservations.add(reservation3);
        testReservations.add(reservation4);


        assertEquals(testReservations, collectionsReservationDAO.getAllReservationsOfThisUser(user1));

    }

    @Test
    void getReservationsOfThisUserById() {
        CollectionsReservationDAO collectionsReservationDAO = new CollectionsReservationDAO();

        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");

        LocalDateTime localDateTime = LocalDateTime.of(2024, 10, 14,11,12);
        Flight flight = new Flight(localDateTime, "Lviv", 12 );

        collectionsReservationDAO.loadingDataFromAFile(TEST_FILE_NAME);
        Reservation testReservations = new Reservation(flight, user1, "Igor", "Melnik");


        assertEquals(testReservations, collectionsReservationDAO.getReservationsOfThisUserById(user1,1));
    }


    @Test
    void deleteReservationById() {
        CollectionsReservationDAO collectionsReservationDAO = new CollectionsReservationDAO();

        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        collectionsReservationDAO.loadingDataFromAFile(TEST_FILE_NAME);

        collectionsReservationDAO.deleteReservationById(user1, 1);
        assertEquals(3, collectionsReservationDAO.getAllReservationsOfThisUser(user1).size());
    }

    @Test
    void saveReservation() {
        CollectionsReservationDAO collectionsReservationDAO = new CollectionsReservationDAO();

        LocalDateTime localDateTime = LocalDateTime.of(2024, 10, 14,11,12);
        Flight flight = new Flight(localDateTime, "Lviv", 12 );

        User user1 = new User("Pavlo", "Barabah", "Павло", "Барабах");
        collectionsReservationDAO.loadingDataFromAFile(TEST_FILE_NAME);

        Reservation reservation5 = new Reservation(flight, user1, "Sasha", "Ivanov");
        collectionsReservationDAO.saveReservation(reservation5);

        assertEquals(5, collectionsReservationDAO.getAllReservationsOfThisUser(user1).size());

    }
}