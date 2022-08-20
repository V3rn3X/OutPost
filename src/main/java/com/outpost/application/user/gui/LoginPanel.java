package com.outpost.application.user.gui;

import com.outpost.application.user.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.awt.*;

@Route("loginPanel")
public class LoginPanel  extends VerticalLayout {

    public LoginPanel() {


        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma"))));
        add(new H2("Welcome in OutPost"));

        TextField username = new TextField();
        username.setLabel("Login");
        username.setPrefixComponent(VaadinIcon.USER.create());

        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Password");


        Button login = new Button("Login");
        Button register = new Button("Register");

        add(username, passwordField);
        add(new HorizontalLayout(login, register));

        login.addClickListener(e ->
                login.getUI().ifPresent(ui ->
                        ui.navigate("hello"))
        );

        register.addClickListener(e ->
                register.getUI().ifPresent(ui ->
                        ui.navigate("register"))
        );


    }
}
