package com.outpost.application.parcellocker.gui;

import com.outpost.application.parcellocker.ParcelLocker;
import com.outpost.application.parcellocker.ParcelLockerList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("deleteParcelLocker")
public class DeleteParcelLocker extends VerticalLayout {

    String toDelete = "";

    public DeleteParcelLocker(ParcelLockerList parcelLockerList) {

        Button menu = new Button("Menu");
        menu.addClickListener(e ->
                menu.getUI().ifPresent(ui ->
                        ui.navigate("hello"))
        );

        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma")), menu));
        add(new H2("Add Parcel Locker"));

        Button deleteParcelLocker = new Button("Delete");

        ComboBox<ParcelLocker> parcelLockerComboBox = new ComboBox<>("Choose Parcel Locker", parcelLockerList.getParcelLockers());
        parcelLockerComboBox.setItems(parcelLockerList.getParcelLockers());
        parcelLockerComboBox.setItemLabelGenerator(ParcelLocker::getName);
        deleteParcelLocker.addClickListener(clickEvent -> {
            toDelete = parcelLockerComboBox.getValue().getName();
            parcelLockerList.deleteParcelLocker(toDelete);

            Notification notification = new Notification(
                    "Parcel Locker " + toDelete + "was deleted", 3000);
            notification.open();
        });


        add(parcelLockerComboBox, deleteParcelLocker);


    }

}
