package org.main;

import org.main.controllers.FlightController;
import org.main.controllers.ReservationController;
import org.main.controllers.UserController;
import org.main.services.FlightService;
import org.main.services.ReservationService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingApp {
    private static final FlightService fs = new FlightService();
    private static final FlightController fc = new FlightController(fs);
    private static final ReservationService rs = new ReservationService();
    private static final ReservationController rc = new ReservationController(rs);
    private static final UserController uc = new UserController();
    private static final Scanner scanner = new Scanner(System.in);
    private boolean run = true;
    private User currentUser;

    private void runApp() throws Exception {
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

        List<Flight> filteredFlights = fc.getFlightByInfo(destination, date, numOfPeople);

        if (!filteredFlights.isEmpty()) {
            System.out.println("Found Flights:");
            for (Flight flight : filteredFlights) {
                System.out.println(flight.prettyFormat());
            }

            System.out.println("Select Flight ID or enter 0 to return to main menu:");
            int choiceId = Integer.parseInt(scanner.nextLine());
            if (choiceId == 0){
                runApp();
            }
            // Call method to display flight by chosen ID
            fc.displayFlightById(choiceId);
            for (int i = 0; i < filteredFlights.size(); i++) {
                book(filteredFlights.get(i), numOfPeople);
            }
        } else {
            System.out.println("No flights found with specified parameters.");
        }
    }



    private void book(Flight flight, int numOfTickets) {
        System.out.println("Enter Passenger Details for Each Ticket:");
        List<String> passengerNames = new ArrayList<>();
        List<String> passengerSurnames = new ArrayList<>();
        User user = null;
        for (int i = 0; i < numOfTickets; i++) {
            System.out.print("Passenger " + (i + 1) + " First Name: ");
            passengerNames.add(scanner.nextLine());
            System.out.print("Passenger " + (i + 1) + " Last Name: ");
            passengerSurnames.add(scanner.nextLine());
            user = new User(passengerNames.get(i), passengerSurnames.get(i));
            rc.addReservation(user, flight);
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
                boolean isAuthenticated = uc.authenticateUser(login, password);
                if (isAuthenticated) {
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

    private void cancelReservation(){
        System.out.print("Enter Reservation ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter First Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String surname = scanner.nextLine();
        currentUser = new User(name, surname);
        rc.deleteReservationById(currentUser, id);

    }

    private void myReservation(){
        System.out.print("Enter First Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String surname = scanner.nextLine();
        User user = new User(name, surname);
        List<Reservation> reservations = rc.getAllReservationsOfThisUser(user);
        if (!reservations.isEmpty()){
            System.out.println(rc.getAllReservationsOfThisUser(user));
        } else {
            System.out.println("No such reservations exist!");
        }
    }

    private void exit(){
        System.out.println("Close the program? Yes/No");
        String s = scanner.nextLine();
        if (s.equals("Yes")){
            run = false;
        } else if (s.equals("No")) {
            run = true;
        } else {
            System.out.println("Error! Please try again!");
        }
    }
}
