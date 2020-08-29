package de.projekt.carlook;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import de.projekt.carlook.util.Views;
import de.projekt.carlook.views.LoginView;
import de.projekt.carlook.views.MainView;
import de.projekt.carlook.views.RegistrationView;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        Navigator navigator = new Navigator(this, this);
        navigator.addView(Views.LOGIN, LoginView.class);
        navigator.addView(Views.REGISTRATION, RegistrationView.class);
        navigator.addView(Views.MAIN, MainView.class);
        //navigator.addView(Views.CARS, CarsView.class);

        UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
