package de.projekt.carlook.views.components;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;
import de.projekt.carlook.dao.entity.Car;

import java.io.File;

public class CarCard extends VerticalLayout {

    private Car car;


    public CarCard(Car car) {
        this.car = car;
        setUp();
    }

    private void setUp(){
        ExternalResource resource = new ExternalResource("https://www.extremetech.com/wp-content/uploads/2019/12/SONATA-hero-option1-764A5360-edit.jpg");
        Image image = new Image(null, resource);
        image.setWidth("450px");

        Label brand = new Label("BWW");
        brand.addStyleName("brand");

        Button detail = new Button("Details");
        detail.addStyleName("detail");
        Button reserve = new Button("Reserve");
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
