package de.projekt.carlook.views;

import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import de.projekt.carlook.controller.AuthCtrl;
import de.projekt.carlook.dao.entity.User;
import de.projekt.carlook.exceptions.AuthFailureException;
import de.projekt.carlook.exceptions.UserNotExistsException;
import de.projekt.carlook.util.Attributes;
import de.projekt.carlook.util.Views;

public class LoginView extends VerticalLayout implements View, Button.ClickListener {

    private FormLayout form;

    private TextField email;

    private PasswordField password;

    private Button login;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

        User user = (User) VaadinSession.getCurrent().getAttribute(Attributes.USER);
        if(user != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        }else {
            setUp();
        }


    }

    public void setUp() {

        setSizeUndefined();
        addStyleName("login-container");

        Label logo = new Label("<p class=\"logo\">Carlook Ltd.</p>", ContentMode.HTML);
        logo.setSizeUndefined();
        addComponent(logo);
        setComponentAlignment(logo, Alignment.TOP_CENTER);

        HorizontalLayout formly = new HorizontalLayout();
        formly.setSizeFull();

        form = new FormLayout();
        form.setSizeUndefined();
        form.addStyleName("form");

        email = new TextField("E-Mail");
        email.setRequiredIndicatorVisible(true);
        email.setSizeFull();
        password = new PasswordField("Password");
        password.setRequiredIndicatorVisible(true);
        password.setSizeFull();

        login = new Button("Sign In");
        login.setSizeFull();
        login.addStyleName("login");
        login.addClickListener(this);

        Label register = new Label("<span>New user? <a href=\"http://localhost:8080/carlooksystem_war/#!registration\">Create an account</a></span>", ContentMode.HTML);

        form.addComponent(email);
        form.addComponent(password);
        form.addComponent(login);
        form.addComponent(register);

        formly.addComponentsAndExpand(form);
        formly.setComponentAlignment(form, Alignment.MIDDLE_CENTER);

        addComponent(formly);
        setComponentAlignment(formly, Alignment.MIDDLE_CENTER);


    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

        String loginEmail = email.getValue();
        String loginPassword = password.getValue();

        if(loginEmail.isEmpty() || loginPassword.isEmpty()){
            Notification.show("Please fill all required fields!", Notification.Type.ERROR_MESSAGE);
        }else {
            try {
                AuthCtrl.authenticate(loginEmail, loginPassword);
            } catch (AuthFailureException | UserNotExistsException e) {
                Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        }


    }
}
