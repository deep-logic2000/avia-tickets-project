package org.main;

import org.main.controllers.FlightController;
import org.main.controllers.ReservationController;
import org.main.controllers.UserController;
import org.main.dao.CollectionFlightsDAO;
import org.main.services.FlightService;
import org.main.services.ReservationService;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingApp {
    private static final FlightService fs = new FlightService(new CollectionFlightsDAO(new File("flights.bin")));
    private static final FlightController fc = new FlightController(fs);
    private static final ReservationService rs = new ReservationService();
    private static final ReservationController rc = new ReservationController(rs);
    private static final UserController uc = new UserController();
    private static final Scanner scanner = new Scanner(System.in);
    private boolean run = true;
    private User currentUser;


    private void runApp() throws Exception {
        rc.loadingDataFromAFile("reservationDataFile.bin");
        while (run){
            getMenu();
            int choice = getChoice();
            switch (choice){
                case 1:
                    getAllFlights();
                    break;
                case 2:
                    getFlightById();
                    break;
                case 3:
                    searchAndBookingFlight();
                    break;
                case 4:
                    cancelReservation();
                    break;
                case 5:
                    myReservation();
                    break;
                case 6:
                    logout();
                    break;
                case 7:
                    exit();
                    break;
                default:
                    System.out.println("Error! Please try again!");
            }

        }
    }

    private int getChoice() throws Exception {
        int choice;
        do {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                return choice;
            } catch (NumberFormatException e){
                System.out.println("Error! Please enter a number!");
                runApp();
            }
        } while(true);
    }

    private void getMenu(){
        System.out.println("1. Flight Board.\n" +
                "2. View Flight Information.\n" +
                "3. Search and Book Flight.\n" +
                "4. Cancel Reservation.\n" +
                "5. My Reservations.\n" +
                "6. End Session.\n" +
                "7. Exit.");
        System.out.print("Choose an option: ");
    }

    private void getAllFlights(){
        fc.displayAllFlights();
    }

    private void getFlightById()  {
        System.out.print("Enter Flight ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            fc.displayFlightById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void searchAndBookingFlight() throws Exception {
        search();

    }

    private void search() throws Exception {
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter Date (e.g., 13/03/2024): ");
        String dateString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        System.out.print("Enter Number of Passengers: ");
        int numOfPeople = Integer.parseInt(scanner.nextLine());

        System.out.println("Found Flights:");
        fc.displayFlightByInfo(destination, date, numOfPeople);

        System.out.println("Select Flight ID or enter 0 to return to main menu:");
        int choiceId = Integer.parseInt(scanner.nextLine());
        if (choiceId == 0) {
            runApp();
        }
        // Call method to display flight by chosen ID
        fc.displayFlightById(choiceId);
        book(new Flight(date.atStartOfDay(), destination, numOfPeople), numOfPeople);

    }



    private void book(Flight flight, int numOfTickets) {
        System.out.println("Enter Passenger Details for Each Ticket:");
        List<String> passengerNames = new ArrayList<>();
        List<String> passengerSurnames = new ArrayList<>();

        for (int i = 0; i < numOfTickets; i++) {
            System.out.print("Passenger " + (i + 1) + " First Name: ");
            String name = scanner.nextLine();
            passengerNames.add(name);

            System.out.print("Passenger " + (i + 1) + " Last Name: ");
            String surname = scanner.nextLine();
            passengerSurnames.add(surname);

            flight.decreaseAmountOfAvailablePlaces();

            Reservation reservation = new Reservation(flight, currentUser, name, surname);
            rc.addReservation(currentUser, flight, name, surname);
        }

        System.out.println("Flight booking completed successfully!");
        System.out.println("Flight Details:");
        System.out.println(flight.prettyFormat());
        System.out.println("Number of Tickets: " + numOfTickets);
        System.out.println("Passengers:");

        for (int i = 0; i < passengerNames.size(); i++) {
            System.out.println(passengerSurnames.get(i) + " " + passengerNames.get(i));
        }
    }


    private void cancelReservation() {
        rc.displayAllReservationsOfThisUser(currentUser);
        List<Reservation> reservations = rc.getAllReservationsOfThisUser(currentUser);
        if(!reservations.isEmpty()) {
            System.out.print("Enter Reservation ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            rc.deleteReservationById(currentUser, id);
            rc.displayAllReservationsOfThisUser(currentUser);
        }
    }


    private void logout() {
        currentUser = null;
        System.out.println("You have been logged out. Please log in to continue.");
        authenticateUser();
    }

    public void authenticateUser() {
        while(run) {
            System.out.print("Enter Login: ");
            String login = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            try {
                User isAuthenticated = uc.authenticateUser(login, password);
                if (isAuthenticated != null) {
                    currentUser = uc.getUserFromData(login, password); // отримати об'єкт користувача з допомогою login
                    runApp();
                } else {
                    System.out.println("Authentication failed. Please try again.");
                }
            } catch (BookingOverflowException e) {
                System.out.println(e.getMessage());
                System.out.println("Authentication failed. Please try again.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void myReservation(){
        System.out.print("Enter First Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String surname = scanner.nextLine();
        User user = new User(name, surname);
        List<Reservation> reservations = rc.getAllReservationsOfThisUser(user);
        if (!reservations.isEmpty()){
            rc.displayAllReservationsOfThisUser(user);
        } else {
            System.out.println("No such reservations exist!");
        }
    }

    private void exit(){
        System.out.println("Close the program? Yes/No");
        String s = scanner.nextLine();
        if (s.equals("Yes")){
            rc.writingDataToAFile(rc.getAllReservations(), "reservationDataFile.bin");
            run = false;
        } else if (s.equals("No")) {
            run = true;
        } else {
            System.out.println("Error! Please try again!");
        }
    }
}
