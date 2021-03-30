package com.reservationsystem.application;

public class PasswordWrongException extends RuntimeException {

PasswordWrongException(){
    super("Password is wrong");
}

}
