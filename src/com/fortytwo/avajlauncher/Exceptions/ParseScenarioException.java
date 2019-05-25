package com.fortytwo.avajlauncher.Exceptions;

public class ParseScenarioException extends Exception {

    public ParseScenarioException(String message) {
        super(message);
    }

    public ParseScenarioException(String message, Throwable err) {
        super(message, err);
    }

    public ParseScenarioException(Throwable err) {
        super(err);
    }
}
