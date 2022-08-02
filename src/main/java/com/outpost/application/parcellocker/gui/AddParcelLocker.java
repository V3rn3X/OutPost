package com.outpost.application.parcellocker.gui;

import com.outpost.application.parcellocker.ParcelLocker;
import com.outpost.application.parcellocker.ParcelLockerList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("addParcelLocker")

public class AddParcelLocker extends VerticalLayout {

    @Autowired
    public AddParcelLocker(ParcelLockerList parcelLockerList) {

        Button menu = new Button("Menu");
        menu.addClickListener(e ->
                menu.getUI().ifPresent(ui ->
                        ui.navigate("hello"))
        );

        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma")), menu));
        add(new H2("Add Parcel Locker"));

        TextField textFieldId = new TextField("ID");
        textFieldId.setClearButtonVisible(true);
        TextField textFieldName = new TextField("Name");
        textFieldName.setClearButtonVisible(true);
        TextField textFieldStreet = new TextField("Street");
        textFieldStreet.setClearButtonVisible(true);
        TextField textFieldCity = new TextField("City");
        textFieldCity.setClearButtonVisible(true);
        TextField textFieldZipcode = new TextField("Zip Code");
        textFieldZipcode.setClearButtonVisible(true);

        Button buttonAdd = new Button("Add new Parcel Locker");

        buttonAdd.addClickListener(clickEvent -> {
            ParcelLocker parcelLocker = new ParcelLocker();
            parcelLocker.setID(textFieldId.getValue());
            parcelLocker.setName(textFieldName.getValue());
            parcelLocker.setStreet(textFieldStreet.getValue());
            parcelLocker.setCity(textFieldCity.getValue());
            parcelLocker.setZipcode(textFieldZipcode.getValue());

            parcelLockerList.getParcelLockers().add(parcelLocker);
            Notification notification = new Notification(
                    "Parcel Locker added", 3000);
            notification.open();
        });

        add(textFieldId, textFieldName, textFieldStreet, textFieldCity, textFieldZipcode, buttonAdd);
    }
}


