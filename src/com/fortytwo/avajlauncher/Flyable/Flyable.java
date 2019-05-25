package com.fortytwo.avajlauncher.Flyable;

import com.fortytwo.avajlauncher.Control.WeatherTower;

public interface Flyable {
    void    updateCondition();
    void    registerTower(WeatherTower WeatherTower);
    Coordinates    getCoordinates();
}
