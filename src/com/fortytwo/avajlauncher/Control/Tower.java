package com.fortytwo.avajlauncher.Control;

import com.fortytwo.avajlauncher.Flyable.Flyable;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Tower {
    private ArrayList<Flyable> observers;
    private Logger logger = Logger.getLogger("SimulatorLogger");

    Tower() {
        observers = new ArrayList<>();
    }

    public void register(Flyable flyable) {
        if (!observers.contains(flyable)) {
            observers.add(flyable);
            logger.info("Tower says: " + flyable.toString() + " registered to weather tower.");
        }
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
        logger.info("Tower says: " + flyable.toString() + " unregistered from weather tower.");
        logger.info(flyable.toString() + "says: I landed, here my coordinates");
        logger.info(flyable.getCoordinates().toString());
    }

    protected void conditionsChanged() {
        ArrayList<Flyable> unregistered = new ArrayList<>();
        for (Flyable flyable : observers) {
            flyable.updateCondition();
            if (flyable.getCoordinates().getHeight() <= 0)
                unregistered.add(flyable);
        }
        for (Flyable flyable : unregistered) {
            unregister(flyable);
        }
    }
}
