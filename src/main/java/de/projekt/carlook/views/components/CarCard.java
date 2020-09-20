package de.projekt.carlook.views.components;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import de.projekt.carlook.controller.ReservationCtrl;
import de.projekt.carlook.dao.entity.Car;
import de.projekt.carlook.dao.entity.User;
import de.projekt.carlook.util.Attributes;

import java.io.File;

public class CarCard extends VerticalLayout {

    private Car car;

    private String imageUrl;


    public CarCard(Car car, String imageUrl) {
        this.car = car;
        this.imageUrl = imageUrl;
        setUp();
    }

    private void setUp(){
        ExternalResource resource = new ExternalResource(imageUrl);
        Image image = new Image(null, resource);
        image.setWidth("450px");

        Label brand = new Label(car.getBrand());
        brand.addStyleName("brand");

        Button detail = new Button("Details");
        detail.addStyleName("detail");
        detail.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().addWindow(new CarDetail(car, imageUrl));
            }
        });
        Button reserve = new Button("Reserve");
        reserve.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                User user = (User) VaadinSession.getCurrent().getAttribute(Attributes.USER);
                ReservationCtrl.reserve(user.getEmail(), car.getId());
                Notification.show("Car successfully reserved!", Notification.Type.HUMANIZED_MESSAGE);
            }
        });
        reserve.addStyleName("reserve");

        HorizontalLayout btnLy = new HorizontalLayout();
        btnLy.addComponent(detail);
        btnLy.addComponent(reserve);
        btnLy.addStyleName("card-btn-ly");

        addComponentsAndExpand(image);
        addComponent(brand);
        addComponent(btnLy);

        setComponentAlignment(brand, Alignment.TOP_CENTER);
    }
}
