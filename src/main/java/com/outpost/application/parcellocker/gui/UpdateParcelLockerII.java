package com.outpost.application.parcellocker.gui;

import com.outpost.application.parcellocker.ParcelLocker;
import com.outpost.application.parcellocker.ParcelLockerList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import static com.outpost.application.parcellocker.EditParcelLocker.getParcelLockerEdit;
import static com.outpost.application.parcellocker.ParcelLockerList.checkNameParcelLocker;

@Route("updateParcelLocker2")
public class UpdateParcelLockerII extends VerticalLayout {

    public UpdateParcelLockerII(ParcelLockerList parcelLockerList) {

        Button menu = new Button("Menu");
        menu.addClickListener(e ->
                menu.getUI().ifPresent(ui ->
                        ui.navigate("hello"))
        );

        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma")), menu));
        add(new H2("Update Parcel Locker"));

        Button cancel = new Button("Cancel");
        cancel.addClickListener(e ->
                cancel.getUI().ifPresent(ui ->
                        ui.navigate("updateParcelLocker"))
        );

        Button save = new Button("Save");
        String temporary = getParcelLockerEdit().getName();


        TextField updateId = new TextField("ID");
       updateId.setValue(getParcelLockerEdit().getID());
        updateId.setPattern("[A-Z]{3}[0-9]{3}");
        updateId.setEnabled(false);
        TextField updateName = new TextField("Name");
        updateName.setValue(getParcelLockerEdit().getName());
        updateName.setPattern("[A-Z]{1}[a-z]{1,}");
        TextField updateStreet = new TextField("Street");
        updateStreet.setValue(getParcelLockerEdit().getStreet());
        updateStreet.setPattern("([A-Z]{1}[a-z]{1,})([ -]{1}[A-Z]{1}[a-z]{1,})*");
        TextField updateCity = new TextField("City");
        updateCity.setValue(getParcelLockerEdit().getCity());
        updateCity.setPattern("([A-Z]{1}[a-z]{1,})([ -]{1}[A-Z]{1}[a-z]{1,})*");
        TextField updateZipcode = new TextField("Zip Code");
        updateZipcode.setValue(getParcelLockerEdit().getZipcode());
        updateZipcode.setPattern("[0-9]{2}[-][0-9]{3}");

        FormLayout formLayout = new FormLayout();
        formLayout.add(
                updateId, updateName,
                updateStreet,
                updateCity, updateZipcode
        );
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("300px", 2)
        );
        formLayout.setColspan(updateStreet, 2);

        save.addClickListener(e -> {

            if (updateId.isInvalid() || updateName.isInvalid() || updateStreet.isInvalid() ||
                    updateCity.isInvalid() || updateZipcode.isInvalid() || updateId.getValue().equals("") ||
                    updateName.getValue().equals("") || updateCity.getValue().equals("") || updateStreet.getValue().equals("") ||
                    updateZipcode.getValue().equals("")){
                Notification notification = new Notification(
                        "Wrong Data", 3000);
                notification.open();


            } else if (checkNameParcelLocker(updateName.getValue(), parcelLockerList.getParcelLockers())) {

                Notification notification = new Notification(
                        "Parcel Locker with that ID or name already exists", 3000);
                notification.open();

            } else {

                for (ParcelLocker parcelLocker : parcelLockerList.getParcelLockers()) {
                    if (parcelLocker.getName().equals(temporary)) {
                        parcelLocker.setID(updateId.getValue());
                        parcelLocker.setName(updateName.getValue());
                        parcelLocker.setStreet(updateStreet.getValue());
                        parcelLocker.setCity(updateCity.getValue());
                        parcelLocker.setZipcode(updateZipcode.getValue());
                    }
                }
                save.getUI().ifPresent(ui ->
                        ui.navigate("updateParcelLocker"));

            }
        });

        add(formLayout);
        add(new HorizontalLayout(cancel, save ));


    }
}
