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

import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;

@Route("deleteParcelLocker")
public class DeleteParcelLocker extends VerticalLayout {

    String testowy = "test";

    public DeleteParcelLocker(ParcelLockerList parcelLockerList) {


        Button menu = new Button("Menu");
        menu.addClickListener(e ->
                menu.getUI().ifPresent(ui ->
                        ui.navigate("hello"))
        );

        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma")), menu));
        add(new H2("Add Parcel Locker"));


//        Select<ParcelLocker> select = new Select<>();
//        select.setLabel("Choose Parcel to delete");
//        select.setItemLabelGenerator(ParcelLocker::getID);
//
//        List<ParcelLocker> parcelLockers = parcelLockerList.getParcelLockers();
//        select.setItems(parcelLockers);


        Button deleteParcelLocker = new Button("Delete " + testowy + " a");
//
//        Select<ParcelLocker> comboBox = new Select<>();
//        comboBox.setItems(parcelLockerList.getParcelLockers());
//        comboBox.setItemLabelGenerator(ParcelLocker::getName);
//
//
//        addParcelLocker.addClickListener(event -> {
//            parcelLockerList.deleteParcelLocker(String.valueOf(comboBox.getValue()));
//                });

        ComboBox<ParcelLocker> parcelLockerComboBox = new ComboBox<>("Nazwa", parcelLockerList.getParcelLockers());
        parcelLockerComboBox.setItems(parcelLockerList.getParcelLockers());
        parcelLockerComboBox.setItemLabelGenerator(ParcelLocker::getName);
        deleteParcelLocker.addClickListener(clickEvent -> {
            testowy = parcelLockerComboBox.getValue().getName();
            parcelLockerList.deleteParcelLocker(testowy);

            Notification notification = new Notification(
                    testowy, 3000);
            notification.open();
        });


        add(parcelLockerComboBox, deleteParcelLocker);


    }

}
