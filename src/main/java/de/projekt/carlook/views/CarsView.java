package de.projekt.carlook.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import de.projekt.carlook.controller.CarCtrl;
import de.projekt.carlook.dao.entity.Car;
import de.projekt.carlook.views.components.CarCard;

import java.util.List;

public class CarsView extends VerticalLayout {

    private HorizontalLayout searchLy;

    private TextField searchBar;

    private Button search;

    private VerticalLayout content;

    private String[] images = new String[]{"https://audimediacenter-a.akamaihd.net/system/production/media/49930/images/28318372b7f78fa640c07e629929a92fffb90804/A178321_full.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/2/2e/BMW_i8.jpg",
            "https://www.honda.de/content/dam/central/cars/Civic-5-door-2020/Ready_for_the_Road_1.jpg/_jcr_content/renditions/c3.jpg"};

    public CarsView(){
        setUp();
    }

    public void setUp(){
        setSearchSection();
        content = new VerticalLayout();
        addComponent(content);
        addCars();
    }

    private void setSearchSection(){
        searchLy = new HorizontalLayout();
        searchLy.setSizeFull();

        searchBar = new TextField();
        searchBar.setPlaceholder("Search ...");
        searchBar.addStyleName("searchbar");

        search = new Button("GO!");
        search.addStyleName("search");
        search.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                displaySearchResult();
            }
        });

        HorizontalLayout searchSection = new HorizontalLayout();
        searchSection.setSpacing(false);
        searchSection.addComponent(searchBar);
        searchSection.addComponent(search);

        searchLy.addComponent(searchSection);
        searchLy.setComponentAlignment(searchSection, Alignment.MIDDLE_CENTER);

        addComponent(searchLy);
    }

    private void addCars() {
        content.removeAllComponents();
        HorizontalLayout row = new HorizontalLayout();
        List<Car> cars = CarCtrl.getAllCars();

        int counter = 0;
        for(Car car : cars){
            CarCard carCard = new CarCard(car, images[counter++]);
            row.addComponent(carCard);
        }

        content.addComponent(row);
    }

    private void displaySearchResult() {
        content.removeAllComponents();
        String filter = searchBar.getValue();
        if(filter.isEmpty()){
            Notification.show("Please enter a search value!", Notification.Type.WARNING_MESSAGE);
        }else {
            List<Car> cars = CarCtrl.search(filter);
            HorizontalLayout result = new HorizontalLayout();
            Button close = new Button("X");
            close.addStyleName("close");
            close.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    addCars();
                }
            });
            if(cars.isEmpty()){
                Label emptyLbl = new Label("<b>No results found for: " + filter + "!</b>", ContentMode.HTML);
                content.addComponent(emptyLbl);
                content.addComponent(close);
                content.setComponentAlignment(emptyLbl, Alignment.TOP_CENTER);
                content.setComponentAlignment(close, Alignment.TOP_RIGHT);
            }else {
                Label msg = new Label("<b>Found <u>" + cars.size() + "</u> results, for <i>" + filter + "</i></b>", ContentMode.HTML);

                content.addComponent(msg);
                content.addComponent(close);
                content.setComponentAlignment(msg, Alignment.TOP_CENTER);
                content.setComponentAlignment(close, Alignment.TOP_RIGHT);
                int counter = 0;
                for(Car car : cars){
                    if(car.getBrand().contains("Audi")) {
                        counter = 0;
                    }else if(car.getBrand().contains("BMW")) {
                        counter = 1;
                    } else {
                        counter = 2;
                    }
                    CarCard carCard = new CarCard(car, images[counter]);
                    result.addComponent(carCard);
                }
                content.addComponent(result);
            }
        }
    }
}
