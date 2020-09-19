package de.projekt.carlook.exceptions;

public class UserNotExistsException extends Exception {


    public UserNotExistsException(String email) {
        super("User with email: " + email + " not exists!");
    }
}
