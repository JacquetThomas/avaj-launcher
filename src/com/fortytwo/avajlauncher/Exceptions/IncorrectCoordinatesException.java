package com.fortytwo.avajlauncher.Exceptions;

public class IncorrectCoordinatesException extends Exception {
    public IncorrectCoordinatesException(String message, Throwable err) {
        super(message, err);
    }

    public IncorrectCoordinatesException(String message) {
        super(message);
    }
}
