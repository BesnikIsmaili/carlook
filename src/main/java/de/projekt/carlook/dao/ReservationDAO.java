package de.projekt.carlook.dao;

import de.projekt.carlook.dao.entity.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO extends AbstractDAO {

    public ReservationDAO(){

    }

    public boolean create(Reservation reservation) {
        try {
            String sql = "INSERT INTO carlook.reservation(car_id, email) VALUES(?, ?)";

            PreparedStatement statement = getPreparedStatement(sql);
            statement.setInt(1, reservation.getCar_id());
            statement.setString(2, reservation.getEmail());

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Reservation read(int id) {
        try {
            String sql = "SELECT id, car_id, email FROM carlook.reservation WHERE id = ?";

            PreparedStatement statement = getPreparedStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Reservation reservation = new Reservation();
                reservation.setId(resultSet.getInt("id"));
                reservation.setCar_id(resultSet.getInt("car_id"));
                reservation.setEmail(resultSet.getString("email"));
                return reservation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> readAllByUser(String email) {
        List<Reservation> reservations = new ArrayList<>();
        try {
            String sql = "SELECT id, car_id, email FROM carlook.reservation WHERE email = ?";

            PreparedStatement statement = getPreparedStatement(sql);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Reservation reservation = new Reservation();
                reservation.setId(resultSet.getInt("id"));
                reservation.setCar_id(resultSet.getInt("car_id"));
                reservation.setEmail(resultSet.getString("email"));
                reservations.add(reservation);
            }
            return reservations;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public Reservation update(Reservation reservation) {
        return null;
    }

    public boolean delete(int id) {
        try {
            String sql = "DELETE FROM carlook.reservation WHERE id = ?";
            PreparedStatement statement = getPreparedStatement(sql);
            statement.setInt(1, id);;
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
