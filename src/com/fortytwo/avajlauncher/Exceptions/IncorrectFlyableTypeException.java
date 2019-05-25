package com.fortytwo.avajlauncher.Exceptions;

public class IncorrectFlyableTypeException extends Exception {

    public IncorrectFlyableTypeException(String message) {
        super(message);
    }

    public IncorrectFlyableTypeException(String message, Throwable err) {
        super(message, err);
    }
}
