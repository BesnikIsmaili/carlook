package de.projekt.carlook.views;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import de.projekt.carlook.controller.CarCtrl;
import de.projekt.carlook.controller.ReservationCtrl;
import de.projekt.carlook.dao.entity.Car;
import de.projekt.carlook.dao.entity.Reservation;
import de.projekt.carlook.dao.entity.ReservedCar;

import java.util.ArrayList;
import java.util.List;

public class ReservationView extends VerticalLayout {


    public ReservationView() {
        setUp();
    }

    private void setUp() {

        List<ReservedCar> reservations = ReservationCtrl.getAllReservationOfUser();
        if(reservations.isEmpty()) {
            Label emptyLbl = new Label("<h3>Reservation list is empty!<h3>", ContentMode.HTML);
            addComponent(emptyLbl);
            setComponentAlignment(emptyLbl, Alignment.TOP_CENTER);
        }else {
            Grid<ReservedCar> grid = new Grid<>();
            grid.setItems(reservations);
            grid.addColumn(ReservedCar::getReservationId).setCaption("Reservation ID");
            grid.addColumn(ReservedCar::getBrand).setCaption("Brand");
            grid.addColumn(ReservedCar::getYear).setCaption("Year");
            grid.addColumn(ReservedCar::getDescription).setCaption("Description");
            grid.setSizeFull();
            addComponent(grid);
        }

    }
}
