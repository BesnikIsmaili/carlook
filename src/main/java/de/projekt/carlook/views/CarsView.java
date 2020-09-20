package de.projekt.carlook.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import de.projekt.carlook.dao.entity.Car;
import de.projekt.carlook.views.components.CarCard;

public class CarsView extends VerticalLayout {

    private HorizontalLayout searchLy;

    private TextField searchBar;

    private Button search;

    public CarsView(){
        setUp();
    }

    public void setUp(){
        setSearchSection();
    }

    private void setSearchSection(){
        searchLy = new HorizontalLayout();
        searchLy.setSizeFull();

        searchBar = new TextField();
        searchBar.setPlaceholder("Search ...");
        searchBar.addStyleName("searchbar");

        search = new Button("GO!");
        search.addStyleName("search");

        HorizontalLayout searchSection = new HorizontalLayout();
        searchSection.setSpacing(false);
        searchSection.addComponent(searchBar);
        searchSection.addComponent(search);

        searchLy.addComponent(searchSection);
        searchLy.setComponentAlignment(searchSection, Alignment.MIDDLE_CENTER);

        addComponent(searchLy);
        addCars();
    }

    private void addCars() {
        HorizontalLayout row = new HorizontalLayout();
        Car car = new Car();
        CarCard carCard = new CarCard(car);
        CarCard carCard2 = new CarCard(car);
        CarCard carCard3 = new CarCard(car);

        row.addComponent(carCard);
        row.addComponent(carCard2);
        row.addComponent(carCard3);

        addComponent(row);
    }
}
