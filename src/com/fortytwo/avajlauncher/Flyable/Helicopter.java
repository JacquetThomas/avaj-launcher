package com.fortytwo.avajlauncher.Flyable;

import com.fortytwo.avajlauncher.Control.WeatherTower;
import com.fortytwo.avajlauncher.Exceptions.IncorrectCoordinatesException;
import com.fortytwo.avajlauncher.Exceptions.NegativeCoordinatesException;

import java.util.logging.Logger;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private Logger logger = Logger.getLogger("SimulatorLogger");

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateCondition() {
        int longitude = coordinates.getLongitude();
        int latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();

        try {
            switch (weatherTower.getWeather(coordinates)){
                case "RAIN":
                    coordinates = new Coordinates(longitude + 5, latitude, height);
                    logger.info(this.toString() + ": I love rainning time 'cause I can create rainy-tornado !");
                    break;
                case "FOG":
                    coordinates = new Coordinates(longitude + 1, latitude, height);
                    logger.info(this.toString() + ": *KOF KOF*");
                    break;
                case "SUN":
                    coordinates = new Coordinates(longitude + 10, latitude, height + 2);
                    logger.info(this.toString() + ": Shine bright like a diamoooond");
                    break;
                case "SNOW":
                    coordinates = new Coordinates(longitude, latitude, height - 12);
                    logger.info(this.toString() + ": I looooove snow 'cause I can create snowy-tornado !");
                    break;
            }
        } catch (NegativeCoordinatesException e) {
            coordinates.setHeight(0);
        } catch (IncorrectCoordinatesException e) {
            coordinates.setHeight(100);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }

    @Override
    public String toString() {
        return ("Helicopter#" + super.toString());
    }
}
