package de.projekt.carlook.views.components;

import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import de.projekt.carlook.dao.entity.Car;

public class CarDetail extends Window {

    private Car car;

    private String image;

    public CarDetail(Car car, String image) {
       this.car = car;
       this.image = image;
       setUp();
    }

    private void setUp() {
        center();
        VerticalLayout content = new VerticalLayout();
        ExternalResource resource = new ExternalResource(image);
        Image image = new Image(null, resource);
        image.setWidth("450px");

        content.addComponent(image);

        Label brand = new Label("<b>Brand: </b>" + car.getBrand(), ContentMode.HTML);
        Label year = new Label("<b>Year: </b>" + car.getYear(), ContentMode.HTML);
        Label description = new Label("<b>Description: </b>", ContentMode.HTML);
        TextArea descriptionArea = new TextArea();
        descriptionArea.setWidth("450px");
        descriptionArea.setValue(car.getDescription());
        descriptionArea.setReadOnly(true);

        content.addComponent(brand);
        content.addComponent(year);
        content.addComponent(description);
        content.addComponent(descriptionArea);

        setContent(content);
        setResizable(false);
        setWidth("450px");

    }
}
