package com.fortytwo.avajlauncher.Flyable;

import java.util.logging.Logger;

public class Aircraft {
    protected long          id;
    protected String        name;
    protected Coordinates   coordinates;
    private static long     idCounter = 0;
    private Logger logger = Logger.getLogger("SimulatorLogger");

    Aircraft(String name, Coordinates coordinates){
        id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    protected long  nextId() {
        idCounter += 1;
        return (idCounter);
    }

    public String    toString() {
        return (name + "(" + id + ")");
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
