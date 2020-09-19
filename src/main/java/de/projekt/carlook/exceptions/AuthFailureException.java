package de.projekt.carlook.exceptions;

public class AuthFailureException extends Exception {

    public AuthFailureException() {
        super("E-Mail or Password is wrong!");
    }
}
