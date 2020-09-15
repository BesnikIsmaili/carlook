package de.projekt.carlook.controller;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import de.projekt.carlook.dao.entity.Account;
import de.projekt.carlook.dao.entity.User;
import de.projekt.carlook.exceptions.AuthFailureException;
import de.projekt.carlook.exceptions.UserAlreadyExistsException;
import de.projekt.carlook.exceptions.UserNotExistsException;
import de.projekt.carlook.services.AuthService;
import de.projekt.carlook.services.UserService;
import de.projekt.carlook.util.Attributes;
import de.projekt.carlook.util.Views;

public class AuthCtrl {

    public static void authenticate(String email, String password) throws AuthFailureException, UserNotExistsException {
       /*
        User user = new User("test@sample.de", "Sample", "Test");
        VaadinSession.getCurrent().setAttribute(Attributes.USER, user);
        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        */

        AuthService authService = new AuthService();
        Account account = new Account(email, password);
        if (authService.checkAuthentication(account)){
            UserService userService = new UserService();
            User user = userService.read(email);
            VaadinSession.getCurrent().setAttribute(Attributes.USER, user);
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        }


    }

    public static void register(String email, String password, String firstname, String lastname) throws UserAlreadyExistsException {
        AuthService authService = new AuthService();
        Account account = new Account(email, password);
        User user = new User(email, firstname, lastname);
        authService.register(user, account);
    }

    public static void logout() {
        VaadinSession.getCurrent().setAttribute(Attributes.USER, null);
        UI.getCurrent().close();
        UI.getCurrent().getPage().setLocation("/carlooksystem_war");

    }
}
