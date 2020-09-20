package de.projekt.carlook.controller;

import de.projekt.carlook.dao.entity.Car;
import de.projekt.carlook.services.CarService;

import java.util.List;

public class CarCtrl {

    private static CarService carService = new CarService();

    public static Car getCarById(int id) {
        return carService.read(id);
    }

    public static List<Car> getAllCars(){ return carService.readAll(); }

    public static List<Car> search(String filter) {
        return carService.filter(filter);
    }
}
