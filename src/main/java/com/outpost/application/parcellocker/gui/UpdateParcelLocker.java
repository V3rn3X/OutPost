package com.outpost.application.parcellocker.gui;

import com.outpost.application.parcellocker.ParcelLocker;
import com.outpost.application.parcellocker.ParcelLockerList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import static com.outpost.application.parcellocker.EditParcelLocker.setParcelLockerEdit;

@Route("updateParcelLocker")
public class UpdateParcelLocker extends VerticalLayout {

    public UpdateParcelLocker(ParcelLockerList parcelLockerList) {

        Button menu = new Button("Menu");
        menu.addClickListener(e ->
                menu.getUI().ifPresent(ui ->
                        ui.navigate("hello"))
        );

        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma")), menu));
        add(new H2("Update Parcel Locker"));

        Button updateParcelLocker = new Button("Edit");
        ComboBox<ParcelLocker> parcelLockerComboBox1 = new ComboBox<>("Choose Parcel Locker", parcelLockerList.getParcelLockers());
        parcelLockerComboBox1.setItems(parcelLockerList.getParcelLockers());
    parcelLockerComboBox1.setItemLabelGenerator(ParcelLocker::getName);
  parcelLockerComboBox1.setValue(parcelLockerList.getParcelLockers().get(0));


        updateParcelLocker.addClickListener(e -> {

                setParcelLockerEdit(parcelLockerComboBox1.getValue());

                updateParcelLocker.getUI().ifPresent(ui ->
                        ui.navigate("updateParcelLocker2"));
        });



        add(parcelLockerComboBox1, updateParcelLocker);


    }
}
