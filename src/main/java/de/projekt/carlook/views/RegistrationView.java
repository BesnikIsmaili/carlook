package de.projekt.carlook.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import de.projekt.carlook.controller.AuthCtrl;
import de.projekt.carlook.exceptions.UserAlreadyExistsException;
import de.projekt.carlook.util.Views;

public class RegistrationView extends VerticalLayout implements View, Button.ClickListener {

    private FormLayout form;

    private TextField firstname;

    private TextField lastname;

    private TextField email;

    private PasswordField password;

    private PasswordField confirmPassword;

    private Button signUp;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setUp();

    }

    public void setUp() {

        setSizeUndefined();
        addStyleName("registration-container");

        Label logo = new Label("<p class=\"logo\">Carlook Ltd.</p>", ContentMode.HTML);
        logo.setSizeUndefined();
        addComponent(logo);
        setComponentAlignment(logo, Alignment.TOP_CENTER);

        HorizontalLayout formly = new HorizontalLayout();
        formly.setSizeFull();

        form = new FormLayout();
        form.setSizeUndefined();
        form.addStyleName("form");

        firstname = new TextField("Firstname");
        firstname.setRequiredIndicatorVisible(true);
        firstname.setSizeFull();
        lastname = new TextField("Lastname");
        lastname.setRequiredIndicatorVisible(true);
        lastname.setSizeFull();
        email = new TextField("E-Mail");
        email.setRequiredIndicatorVisible(true);
        email.setSizeFull();
        password = new PasswordField("Password");
        password.setRequiredIndicatorVisible(true);
        password.setSizeFull();
        confirmPassword = new PasswordField("Confirm Password");
        confirmPassword.setRequiredIndicatorVisible(true);
        confirmPassword.setSizeFull();

        signUp = new Button("Sign Up");
        signUp.setSizeFull();
        signUp.addStyleName("signup");
        signUp.addClickListener(this);

        Label signIn = new Label("<span>You have already account? <a href=\"http://localhost:8080/carlook_war/#!login\">Sign In</a></span>", ContentMode.HTML);

        form.addComponent(firstname);
        form.addComponent(lastname);
        form.addComponent(email);
        form.addComponent(password);
        form.addComponent(confirmPassword);
        form.addComponent(signUp);
        form.addComponent(signIn);

        formly.addComponentsAndExpand(form);
        formly.setComponentAlignment(form, Alignment.MIDDLE_CENTER);

        addComponent(formly);
        setComponentAlignment(formly, Alignment.MIDDLE_CENTER);


    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

        String regFirstname = firstname.getValue();
        String regLastname = lastname.getValue();
        String regEmail = email.getValue();
        String regPassword = password.getValue();
        String regConfPassword = confirmPassword.getValue();

        if (regFirstname.isEmpty() || regLastname.isEmpty() || regEmail.isEmpty()
                || regPassword.isEmpty() || regConfPassword.isEmpty()){
            Notification.show("Please fill all required fields!", Notification.Type.ERROR_MESSAGE);
        }else if(!regPassword.equals(regConfPassword)) {
            Notification.show("Passwords are not same!", Notification.Type.ERROR_MESSAGE);
        }else {
            try {
                AuthCtrl.register(regEmail, regPassword, regFirstname, regLastname);
                Notification.show("Registration success!", Notification.Type.HUMANIZED_MESSAGE);
                UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
            } catch (UserAlreadyExistsException e) {
                Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        }
    }

}
