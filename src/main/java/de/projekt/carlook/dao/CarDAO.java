package de.projekt.carlook.dao;

import de.projekt.carlook.dao.entity.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO extends AbstractDAO {


    public CarDAO(){

    }

    public boolean create(Car car) {
        try {
            String sql = "INSERT INTO carlook.car(brand, description, year) VALUES(?, ?, ?)";

            PreparedStatement statement = getPreparedStatement(sql);
            statement.setString(1, car.getBrand());
            statement.setString(2, car.getDescription());
            statement.setInt(3, car.getYear());

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Car read(int id) {
        try {
            String sql = "SELECT id, brand, description, year FROM carlook.car WHERE id = ?";

            PreparedStatement statement = getPreparedStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setDescription(resultSet.getString("description"));
                car.setYear(resultSet.getInt("year"));
                return car;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Car> readAll() {
        List<Car> cars = new ArrayList<>();
        try {
            String sql = "SELECT id, brand, description, year FROM carlook.car";

            PreparedStatement statement = getPreparedStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setDescription(resultSet.getString("description"));
                car.setYear(resultSet.getInt("year"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public List<Car> filter(String filter) {
        List<Car> cars = new ArrayList<>();
        try {
            String sql = "SELECT id, brand, description, year FROM carlook.car WHERE brand LIKE '%" + filter + "%' OR description LIKE '%" + filter + "%'";

            PreparedStatement statement = getPreparedStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setDescription(resultSet.getString("description"));
                car.setYear(resultSet.getInt("year"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Car update(Car car) {
        return null;
    }

    public boolean delete(int id) {
        return false;
    }
}
