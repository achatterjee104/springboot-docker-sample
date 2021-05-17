package org.thoughtworks.induction.sample.exception;

public class InvalidEmailException extends Exception {
    public InvalidEmailException() {
        super("User with same email already created");
    }
}
