package com.outpost.application.parcellocker.gui;

import com.outpost.application.parcellocker.ParcelLocker;
import com.outpost.application.parcellocker.ParcelLockerList;
import com.vaadin.flow.component.UI;
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
        add(new H2("Delete Parcel Locker"));

        Button deleteParcelLocker = new Button("Delete");

        ComboBox<ParcelLocker> parcelLockerComboBox = new ComboBox<>("Choose Parcel Locker", parcelLockerList.getParcelLockers());
        parcelLockerComboBox.setItems(parcelLockerList.getParcelLockers());
        parcelLockerComboBox.setItemLabelGenerator(ParcelLocker::getName);
        parcelLockerComboBox.setPlaceholder(parcelLockerList.getParcelLockers().get(0).getName());
        parcelLockerComboBox.setValue(parcelLockerList.getParcelLockers().get(0));
        deleteParcelLocker.addClickListener(clickEvent -> {

            toDelete = parcelLockerComboBox.getValue().getName();
            parcelLockerList.deleteParcelLocker(toDelete);

            if (parcelLockerList.getParcelLockers().size() == 0) {
                Notification notification1 = new Notification(
                        "You are delete all Parcel Locker", 6000);
                notification1.open();
                menu.getUI().ifPresent(ui ->
                        ui.navigate("hello"));
            } else {
                UI.getCurrent().getPage().reload();
            }
        });
        add(parcelLockerComboBox, deleteParcelLocker);
    }
}
