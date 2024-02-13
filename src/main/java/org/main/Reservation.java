package org.main;

public class Reservation {

    private String passengerName;
    private String passengerSurname;
    private int flightNumber;
    private User user;
    private double price;
    private int seatNumber;

    public Reservation(String passengerName, String passengerSurname, int flightNumber, User user, double price, int seatNumber) {
        this.passengerName = passengerName;
        this.passengerSurname = passengerSurname;
        this.flightNumber = flightNumber;
        this.user = user;
        this.price = price;
        this.seatNumber = seatNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerSurname() {
        return passengerSurname;
    }

    public void setPassengerSurname(String passengerSurname) {
        this.passengerSurname = passengerSurname;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String prettyFormat() {
        StringBuilder pf = new StringBuilder();

        pf.append("   Passenger name: ").append(passengerName);
        pf.append("   Passenger surname: ").append(passengerSurname);
        pf.append("   Flight number: ").append(flightNumber);
        pf.append("   Price: ").append(price);
        pf.append("   Seat number: ").append(seatNumber);

        return  pf.toString();
    }
}
