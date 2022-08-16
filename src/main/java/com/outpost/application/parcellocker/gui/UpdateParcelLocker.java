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

import static com.outpost.application.parcellocker.EditParcelLocker.setParcelLockerEdit;

@Route("updateParcelLocker")
public class UpdateParcelLocker extends VerticalLayout {

    ParcelLocker toUpdate;
    int index;

    public UpdateParcelLocker(ParcelLockerList parcelLockerList) {

        Button menu = new Button("Menu");
        menu.addClickListener(e ->
                menu.getUI().ifPresent(ui ->
                        ui.navigate("hello"))
        );

        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma")), menu));
        add(new H2("Update Parcel Locker"));


        Button updateParcelLocker = new Button("Edit");
        Button saveParcelLocker = new Button("Save");
        saveParcelLocker.setEnabled(false);
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




        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

//        TextField updateId = new TextField("ID");
//        updateId.setPlaceholder("N/A");
//        updateId.setRequiredIndicatorVisible(true);
//        updateId.setErrorMessage("This field is required");
//        TextField updateName = new TextField("Name");
//        updateName.setPlaceholder("N/A");
//        TextField updateStreet = new TextField("Street");
//        updateStreet.setPlaceholder("N/A");
//        TextField updateCity = new TextField("City");
//        updateCity.setPlaceholder("N/A");
//        TextField updateZipcode = new TextField("Zip Code");
//        updateZipcode.setPlaceholder("N/A");
//
//
//
//        FormLayout formLayout = new FormLayout();
//        formLayout.add(
//                updateId, updateName,
//                updateStreet,
//                updateCity, updateZipcode
//        );
//        formLayout.setResponsiveSteps(
//                // Use one column by default
//                new FormLayout.ResponsiveStep("0", 1),
//                // Use two columns, if layout's width exceeds 500px
//                new FormLayout.ResponsiveStep("300px", 2)
//        );
//        formLayout.setColspan(updateStreet, 2);
//


        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

//        int editActive = 0;
//        ComboBox<ParcelLocker> parcelLockerComboBox1 = new ComboBox<>("Choose Parcel Locker", parcelLockerList.getParcelLockers());
//        parcelLockerComboBox1.setItems(parcelLockerList.getParcelLockers());
//        parcelLockerComboBox1.setItemLabelGenerator(ParcelLocker::getName);
//        parcelLockerComboBox1.setValue(parcelLockerList.getParcelLockers().get(0));
//        updateParcelLocker.addClickListener(clickEvent -> {
//                toUpdate = parcelLockerComboBox1.getValue();
//                updateId.setPlaceholder(toUpdate.getID());
//                updateName.setPlaceholder(toUpdate.getName());
//                updateStreet.setPlaceholder(toUpdate.getStreet());
//                updateCity.setPlaceholder(toUpdate.getCity());
//                updateZipcode.setPlaceholder(toUpdate.getZipcode());
//            updateParcelLocker.setEnabled(false);
//            saveParcelLocker.setEnabled(true);
//            parcelLockerComboBox1.setEnabled(false);
//        });
//
//        saveParcelLocker.addClickListener(clickEvent -> {
//
//            for (ParcelLocker parcelLocker : parcelLockerList.getParcelLockers()) {
//                if (parcelLocker.getName().equals(parcelLockerComboBox1.getValue().getName())) {
//                    parcelLocker.setID(updateId.getValue());
//                    parcelLocker.setName(updateName.getValue());
//                    parcelLocker.setStreet(updateStreet.getValue());
//                    parcelLocker.setCity(updateCity.getValue());
//                    parcelLocker.setZipcode(updateZipcode.getValue());
//                }
//            }
//
//            updateName.setPlaceholder(toUpdate.getName());
//            updateStreet.setPlaceholder(toUpdate.getStreet());
//            updateCity.setPlaceholder(toUpdate.getCity());
//            updateZipcode.setPlaceholder(toUpdate.getZipcode());
//
//
//            updateParcelLocker.setEnabled(true);
//            saveParcelLocker.setEnabled(false);
//            parcelLockerComboBox1.setEnabled(true);
//            parcelLockerComboBox1.setValue(parcelLockerList.getParcelLockers().get(0));
//        });




//
//
//        add(parcelLockerComboBox1, updateParcelLocker, saveParcelLocker, formLayout);
//

    }
}
