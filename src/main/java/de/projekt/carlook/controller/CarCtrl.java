package de.projekt.carlook.controller;

import de.projekt.carlook.dao.entity.Car;
import de.projekt.carlook.services.CarService;

import java.util.List;

public class CarCtrl {

    private static CarService carService = new CarService();

    public static Car getCarById(int id) {
        return carService.read(id);
    }
}
