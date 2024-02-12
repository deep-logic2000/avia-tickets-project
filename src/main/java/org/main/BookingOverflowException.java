package org.main;

public class BookingOverflowException extends RuntimeException {

    public BookingOverflowException(String message){
        super(message);
    }
}
