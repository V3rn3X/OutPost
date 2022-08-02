package com.outpost.application.parcellocker.gui;

import com.outpost.application.parcellocker.ParcelLocker;
import com.outpost.application.parcellocker.ParcelLockerList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("updateParcelLocker")
public class UpdateParcelLocker extends VerticalLayout {

    String toUpdate = "";

    public UpdateParcelLocker(ParcelLockerList parcelLockerList) {

        Button menu = new Button("Menu");
        menu.addClickListener(e ->
                menu.getUI().ifPresent(ui ->
                        ui.navigate("hello"))
        );

        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma")), menu));
        add(new H2("Update Parcel Locker"));


        Button updateParcelLocker = new Button("Edit");

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

        TextField updateId = new TextField("ID");
        updateId.setPlaceholder("N/A");
        TextField updateName = new TextField("Name");
        updateName.setPlaceholder("N/A");
        TextField updateStreet = new TextField("Street");
        updateStreet.setPlaceholder("N/A");
        TextField updateCity = new TextField("City");
        updateCity.setPlaceholder("N/A");
        TextField updateZipcode = new TextField("Zip Code");
        updateZipcode.setPlaceholder("N/A");

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

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

        ComboBox<ParcelLocker> parcelLockerComboBox = new ComboBox<>("Choose Parcel Locker", parcelLockerList.getParcelLockers());
        parcelLockerComboBox.setItems(parcelLockerList.getParcelLockers());
        parcelLockerComboBox.setItemLabelGenerator(ParcelLocker::getName);
        updateParcelLocker.addClickListener(clickEvent -> {
            toUpdate = parcelLockerComboBox.getValue().getName();

            updateId.setPlaceholder(parcelLockerList.updateParcelLocker(parcelLockerComboBox.getValue().getName()));

        });




        add(parcelLockerComboBox, updateParcelLocker, formLayout);


    }
}
