package de.projekt.carlook.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ContactView extends VerticalLayout {

    public ContactView() {
        setUp();
    }

    private void setUp() {
        VerticalLayout contact = new VerticalLayout();
        contact.addStyleName("contact");

        Label msg = new Label("<p>Any questions? You can contact me by the following contact data:</p>", ContentMode.HTML);
        Label logo = new Label("<p class=\"logo\">Carlook Ltd.</p>", ContentMode.HTML);
        logo.setSizeUndefined();

        Label owner = new Label("<b>Muster Owner</b>", ContentMode.HTML);

        Label phone = new Label(VaadinIcons.PHONE.getHtml() + " <b>0231 802384</b>", ContentMode.HTML);

        Label email = new Label(VaadinIcons.PIN_POST.getHtml() + " <b>owner@carlook.de</b>", ContentMode.HTML);

        contact.addComponent(msg);
        contact.addComponent(logo);
        contact.addComponent(owner);
        contact.addComponent(phone);
        contact.addComponent(email);

        addComponentsAndExpand(contact);
        setComponentAlignment(contact, Alignment.TOP_CENTER);
    }
}
