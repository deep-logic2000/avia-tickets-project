package org.main;

import java.io.Serializable;
import java.util.Objects;

public class Reservation implements Serializable {

    private Flight flight;
    private User user;
    private String name;
    private String surname;

    public Reservation(Flight flight, User user, String name, String surname) {
        this.flight = flight;
        this.user = user;
        this.name = name;
        this.surname = surname;
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



    public String prettyFormat() {
        StringBuilder pf = new StringBuilder();

        pf.append("   Passenger name: ").append(name + "\n");
        pf.append("   Passenger surname: ").append(surname + "\n");
        pf.append(flight.prettyFormat() + "\n");
        pf.append("--------------------------------------------------------------------------------------------------");

        return pf.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation reservation = (Reservation) o;
        return Objects.equals(flight, reservation.flight) && Objects.equals(user, reservation.user)
                && Objects.equals(name, reservation.name) && Objects.equals(surname, reservation.surname);
    }



    @Override
    public int hashCode() {return Objects.hash(flight, user, name, surname);}


    @Override
    public String toString() {
        return "Reservation{" +
                "flight=" + flight +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
