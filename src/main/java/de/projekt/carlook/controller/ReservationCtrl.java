package de.projekt.carlook.controller;

import com.vaadin.server.VaadinSession;
import de.projekt.carlook.dao.entity.Reservation;
import de.projekt.carlook.dao.entity.ReservedCar;
import de.projekt.carlook.dao.entity.User;
import de.projekt.carlook.services.ReservationService;
import de.projekt.carlook.util.Attributes;

import java.util.ArrayList;
import java.util.List;

public class ReservationCtrl {

    private static ReservationService reservationService = new ReservationService();


    public static boolean reserve(String email, int car_id) {
        Reservation reservation = new Reservation();
        reservation.setEmail(email);
        reservation.setCar_id(car_id);

        return reservationService.create(reservation);
    }

    public static List<ReservedCar> getAllReservationOfUser() {
        //return new ArrayList<>();

        User user = (User) VaadinSession.getCurrent().getAttribute(Attributes.USER);
        return reservationService.readByUser(user.getEmail());

    }

    public static boolean delete(int id) {
        return reservationService.delete(id);
    }
}
