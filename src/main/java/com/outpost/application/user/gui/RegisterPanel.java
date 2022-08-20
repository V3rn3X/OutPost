package com.outpost.application.user.gui;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("register")
public class RegisterPanel extends VerticalLayout {

    public RegisterPanel() {


        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma"))));
        add(new H2("Welcome in OutPost"));
        add(new H4("Provide new user data"));


        TextField login = new TextField();
        PasswordField password = new PasswordField();
        TextField firstName = new TextField();
        TextField lastName = new TextField();


        add(login, password, firstName, lastName);


    }
}

