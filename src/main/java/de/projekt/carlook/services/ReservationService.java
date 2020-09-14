package de.projekt.carlook.services;

import de.projekt.carlook.dao.CarDAO;
import de.projekt.carlook.dao.ReservationDAO;
import de.projekt.carlook.dao.UserDAO;
import de.projekt.carlook.dao.entity.Car;
import de.projekt.carlook.dao.entity.Reservation;
import de.projekt.carlook.dao.entity.ReservedCar;
import de.projekt.carlook.dao.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private ReservationDAO reservationDAO;

    private UserDAO userDAO;

    private CarDAO carDAO;

    public ReservationService() {
        this.reservationDAO = new ReservationDAO();
        this.userDAO = new UserDAO();
        this.carDAO = new CarDAO();
    }

    public boolean create(Reservation reservation){
        User existUser = userDAO.read(reservation.getEmail());
        Car existCar = carDAO.read(reservation.getCar_id());

        if(existUser == null || existCar == null) {
            // TODO throw exception
            return false;
        }

        return reservationDAO.create(reservation);
    }

    public Reservation read(int id) {
        Reservation reservation = reservationDAO.read(id);
        if(reservation == null){
            // TODO throw exception
            return null;
        }
        return reservation;
    }

    public List<ReservedCar> readByUser(String email) {
        List<ReservedCar> reservedCars = new ArrayList<>();
        List<Reservation> reservations = reservationDAO.readAllByUser(email);
        for(Reservation reservation : reservations) {
            ReservedCar reservedCar = new ReservedCar(reservation.getId(), carDAO.read(reservation.getCar_id()));
            reservedCars.add(reservedCar);
        }
        return reservedCars;
    }

    public boolean delete(int id) {
        return reservationDAO.delete(id);
    }
}
