package com.fortytwo.avajlauncher.Exceptions;

public class NegativeCoordinatesException extends Exception {
    public NegativeCoordinatesException(String message, Throwable err) {
        super(message, err);
    }
    public NegativeCoordinatesException(String message) {
        super(message);
    }
}
