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
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.router.Route;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;
import org.springframework.beans.factory.annotation.Autowired;

import static com.outpost.application.parcellocker.ParcelLockerList.checkIdParcelLocker;
import static com.outpost.application.parcellocker.ParcelLockerList.checkNameParcelLocker;


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

        Button verify = new Button("Check");
        Button cancel = new Button("Cancel");
        cancel.setEnabled(false);

        TextField textFieldId = new TextField("ID");
        textFieldId.setClearButtonVisible(true);
        textFieldId.setPattern("[A-Z]{3}[0-9]{3}");
        TextField textFieldName = new TextField("Name");
        textFieldName.setClearButtonVisible(true);
        textFieldName.setPattern("[A-Z][a-z]*");
        TextField textFieldStreet = new TextField("Street");
        textFieldStreet.setClearButtonVisible(true);
        textFieldStreet.setPattern("([A-Z]{1}[a-z]{1,})([ -]{1}[A-Z]{1}[a-z]{1,})*");
        TextField textFieldCity = new TextField("City");
        textFieldCity.setClearButtonVisible(true);
        textFieldCity.setPattern("([A-Z]{1}[a-z]{1,})([ -]{1}[A-Z]{1}[a-z]{1,})*");
        TextField textFieldZipcode = new TextField("Zip Code");
        textFieldZipcode.setClearButtonVisible(true);
        textFieldZipcode.setPattern("[0-9]{2}[-][0-9]{3}");

        Button buttonAdd = new Button("Add new Parcel Locker");
        buttonAdd.setEnabled(false);

        verify.addClickListener(clickEvent -> {

            if (textFieldId.isInvalid() || textFieldName.isInvalid() || textFieldStreet.isInvalid() ||
                    textFieldCity.isInvalid() || textFieldZipcode.isInvalid() || textFieldId.getValue().equals("") ||
                    textFieldName.getValue().equals("") || textFieldCity.getValue().equals("") || textFieldStreet.getValue().equals("") ||
                    textFieldZipcode.getValue().equals("")){
                Notification notification = new Notification(
                        "Wrong Data", 3000);
                notification.open();
            }
                else if (checkNameParcelLocker(textFieldName.getValue(), parcelLockerList.getParcelLockers()) ||
                    checkIdParcelLocker(textFieldId.getValue(), parcelLockerList.getParcelLockers())) {

                    Notification notification = new Notification(
                            "Parcel Locker with that ID or name already exists", 3000);
                    notification.open();

            } else {
                textFieldId.setEnabled(false);
                textFieldName.setEnabled(false);
                textFieldStreet.setEnabled(false);
                textFieldCity.setEnabled(false);
                textFieldZipcode.setEnabled(false);
                buttonAdd.setEnabled(true);
                cancel.setEnabled(true);
            }
        });

        cancel.addClickListener(clickEvent -> {
            textFieldId.setEnabled(true);
            textFieldName.setEnabled(true);
            textFieldStreet.setEnabled(true);
            textFieldCity.setEnabled(true);
            textFieldZipcode.setEnabled(true);
            buttonAdd.setEnabled(false);
            cancel.setEnabled(false);
        });

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

            buttonAdd.setEnabled(false);
            textFieldId.setEnabled(true);
            textFieldName.setEnabled(true);
            textFieldStreet.setEnabled(true);
            textFieldCity.setEnabled(true);
            textFieldZipcode.setEnabled(true);
            cancel.setEnabled(false);
        });

        add(textFieldId, textFieldName, textFieldStreet, textFieldCity, textFieldZipcode);
        add(new HorizontalLayout(buttonAdd));
        add(new HorizontalLayout(verify, cancel));
    }
}


