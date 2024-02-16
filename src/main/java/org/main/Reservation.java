package org.main;

import java.io.Serializable;
import java.util.Objects;

public class Reservation implements Serializable {

    private Flight flight;
    private User user;
    private double price;
    private int seatNumber;

    public Reservation(Flight flight, User user, double price, int seatNumber) {

        this.flight = flight;
        this.user = user;
        this.price = price;
        this.seatNumber = seatNumber;
    }


    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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

        pf.append("   Passenger name: ").append(user.getName());
        pf.append("   Passenger surname: ").append(user.getSurname());
        pf.append("   Price: ").append(price);
        pf.append("   Seat number: ").append(seatNumber);
        pf.append(flight.prettyFormat());

        return pf.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation reservation = (Reservation) o;
        return Objects.equals(flight, reservation.flight) && Objects.equals(user, reservation.user)
                && Objects.equals(price, reservation.price) && Objects.equals(seatNumber, reservation.seatNumber);
    }



    @Override
    public int hashCode() {return Objects.hash(flight, user, price, seatNumber);}




    @Override
    public String toString() {
        return "Reservation{" +
                "flightNumber=" + flight +
                ", user=" + user +
                ", price=" + price +
                ", seatNumber=" + seatNumber +
                '}';
    }
}
