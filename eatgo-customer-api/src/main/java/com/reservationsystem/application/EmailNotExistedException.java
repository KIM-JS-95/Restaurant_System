package com.reservationsystem.application;

public class EmailNotExistedException extends RuntimeException {

    EmailNotExistedException(String email){
        super("Password is wrong" + email);
    }

}
