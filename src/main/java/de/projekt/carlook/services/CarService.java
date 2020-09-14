package de.projekt.carlook.services;

import de.projekt.carlook.dao.CarDAO;
import de.projekt.carlook.dao.entity.Car;
import de.projekt.carlook.exceptions.UserAlreadyExistsException;
import de.projekt.carlook.exceptions.UserNotExistsException;

import java.util.List;

public class CarService {

    private CarDAO carDAO;

    public CarService(){
        carDAO = new CarDAO();
    }


    public boolean create(Car car)  {
        return carDAO.create(car);
    }

    public Car read(int id)  {
        Car car = carDAO.read(id);
        if(car == null) {
            // TODO throw exception
            return null;
        }
        return car;
    }

    public List<Car> readAll() {
        return carDAO.readAll();
    }

    public List<Car> filter(String filter) {
        return carDAO.filter(filter);
    }

    public Car update(Car car) {
        return carDAO.update(car);
    }

    public boolean delete(int id) {
        return carDAO.delete(id);
    }
}
