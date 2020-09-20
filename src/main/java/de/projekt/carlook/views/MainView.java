package de.projekt.carlook.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import de.projekt.carlook.controller.AuthCtrl;
import de.projekt.carlook.dao.entity.User;
import de.projekt.carlook.util.Attributes;


public class MainView extends VerticalLayout implements View {

    private VerticalLayout content;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setUp();
    }

    public void setUp(){
        setHeader();
        setMenuBar();
        content = new VerticalLayout();
        addComponent(content);
        setCarsView();

    }

    private void setHeader(){
        setMargin(false);
        setSpacing(false);
        HorizontalLayout header = new HorizontalLayout();
        header.setMargin(false);
        header.setSizeFull();
        header.addStyleName("header");
        Label logo = new Label("<p class=\"header-logo\">Carlook Ltd.</p>", ContentMode.HTML);
        logo.setSizeUndefined();
        header.addComponent(logo);
        header.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);

        addComponent(header);

    }

    private void setMenuBar() {
        User user = (User) VaadinSession.getCurrent().getAttribute(Attributes.USER);
        HorizontalLayout menuLy = new HorizontalLayout();
        menuLy.setSizeFull();
        menuLy.addStyleName("menu-container");

        MenuBar menuBar = new MenuBar();
        menuBar.addStyleName("header-menu-bar");
        menuBar.addItem("Cars", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                setCarsView();
            }
        });
        menuBar.addItem("My Reservations", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                setReservationView();
            }
        });
        menuBar.addItem("Contact", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                setContactView();
            }
        });

        MenuBar setting = new MenuBar();
        setting.addStyleName("setting");
        MenuBar.MenuItem settingItem = setting.addItem("Welcome " + user.getLastname() + " !", null);
        settingItem.addItem("Sign Out", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                AuthCtrl.logout();
            }
        });

        menuLy.addComponent(menuBar);
        menuLy.addComponent(setting);

        menuLy.setComponentAlignment(setting, Alignment.MIDDLE_RIGHT);
        menuLy.setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);

        addComponent(menuLy);
    }

    private void setCarsView(){
        content.removeAllComponents();
        content.addComponent(new CarsView());
    }

    private void setReservationView() {
        content.removeAllComponents();
        content.addComponent(new ReservationView());
    }

    private void setContactView() {
        content.removeAllComponents();
        content.addComponent(new ContactView());
    }

}
