package de.projekt.carlook.views;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import de.projekt.carlook.controller.CarCtrl;
import de.projekt.carlook.controller.ReservationCtrl;
import de.projekt.carlook.dao.entity.Car;
import de.projekt.carlook.dao.entity.Reservation;
import de.projekt.carlook.dao.entity.ReservedCar;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReservationView extends VerticalLayout {

    private Grid<ReservedCar> grid;

    private VerticalLayout content;


    public ReservationView() {
        setUp();
        content = new VerticalLayout();
        addComponent(content);
        displayGrid();
    }

    private void setUp() {

        List<ReservedCar> reservations = ReservationCtrl.getAllReservationOfUser();
        if(reservations.isEmpty()) {
            Label emptyLbl = new Label("<h3>Reservation list is empty!<h3>", ContentMode.HTML);
            addComponent(emptyLbl);
            setComponentAlignment(emptyLbl, Alignment.TOP_CENTER);
        }else {

            Button cancel = new Button("Cancel");
            cancel.addStyleName("cancel");
            cancel.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    Set<ReservedCar> cancelReservations = grid.getSelectedItems();
                    if(cancelReservations.isEmpty()){
                       Notification.show("Please select reservation to cancel!", Notification.Type.HUMANIZED_MESSAGE);
                    }else {
                        for(ReservedCar reservedCar: cancelReservations){
                            ReservationCtrl.delete(reservedCar.getReservationId());
                        }
                        displayGrid();
                    }
                }
            });
            addComponent(cancel);
            setComponentAlignment(cancel, Alignment.TOP_CENTER);


        }

    }

    private void displayGrid() {
        List<ReservedCar> reservations = ReservationCtrl.getAllReservationOfUser();
        content.removeAllComponents();
        grid = new Grid<>();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setItems(reservations);
        grid.addColumn(ReservedCar::getReservationId).setCaption("Reservation ID");
        grid.addColumn(ReservedCar::getBrand).setCaption("Brand");
        grid.addColumn(ReservedCar::getYear).setCaption("Year");
        grid.addColumn(ReservedCar::getDescription).setCaption("Description");
        grid.setSizeFull();
        content.addComponent(grid);

    }
}
