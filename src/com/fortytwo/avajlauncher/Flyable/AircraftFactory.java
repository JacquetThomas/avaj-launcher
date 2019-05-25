package com.fortytwo.avajlauncher.Flyable;

import com.fortytwo.avajlauncher.Exceptions.IncorrectCoordinatesException;
import com.fortytwo.avajlauncher.Exceptions.IncorrectFlyableTypeException;
import com.fortytwo.avajlauncher.Exceptions.NegativeCoordinatesException;

public class AircraftFactory {
    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws NegativeCoordinatesException, IncorrectCoordinatesException, IncorrectFlyableTypeException {
        Flyable flyable;

        // Doesn't handle negative coordinate exception
        Coordinates coord = new Coordinates(longitude, latitude, height);

        switch (type) {
            case "Helicopter":
                flyable = new Helicopter(name, coord);
                break;
            case "JetPlane":
                flyable = new JetPlane(name, coord);
                break;
            case "Baloon":
                flyable = new Baloon(name, coord);
                break;
            default:
                throw new IncorrectFlyableTypeException(type + " is not a valid aircraft type.");
        }

        return (flyable);
    }
}
