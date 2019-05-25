package com.fortytwo.avajlauncher.Flyable;

import com.fortytwo.avajlauncher.Exceptions.IncorrectCoordinatesException;
import com.fortytwo.avajlauncher.Exceptions.NegativeCoordinatesException;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) throws NegativeCoordinatesException, IncorrectCoordinatesException {
        if (longitude < 0)
            throw new NegativeCoordinatesException("Longitude : " + longitude);
        else
            this.longitude = longitude;
        if (latitude < 0)
            throw new NegativeCoordinatesException("Latitude : " + latitude);
        else
            this.latitude = latitude;

        if (height < 0)
            throw new NegativeCoordinatesException("Height : " + height);
        else
        {
            if (height > 100)
                throw new IncorrectCoordinatesException("Height must be between 0 and 100. Actual value: " + height);
            else
                this.height = height;
        }
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String toString() {
        return ("Coordinates : Longitude=" + longitude + " Latitude=" + latitude + " Height=" + height);
    }
}
