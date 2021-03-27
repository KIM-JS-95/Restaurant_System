package com.reservationsystem.interfaces;

public class EmailExistedException extends RuntimeException {

    EmailExistedException(String email){
        super("Email is already registered: " + email);
    }
}
