package com.outpost.application.box.gui;

import com.outpost.application.box.Box;
import com.outpost.application.box.BoxList;
import com.outpost.application.box.BoxSize;
import com.outpost.application.parcellocker.ParcelLocker;
import com.outpost.application.parcellocker.ParcelLockerList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;

import static com.outpost.application.box.BoxList.checkIdBox;
import static com.outpost.application.box.EditBox.getBoxEdit;
import static com.outpost.application.parcellocker.EditParcelLocker.getParcelLockerEdit;
import static com.outpost.application.parcellocker.ParcelLockerList.checkNameParcelLocker;

@Route("updateBox2")
public class UpdateBoxII extends VerticalLayout {

    public UpdateBoxII(BoxList boxList, ParcelLockerList parcelLockerList) {

        Button menu = new Button("Menu");
        menu.addClickListener(e ->
                menu.getUI().ifPresent(ui ->
                        ui.navigate("hello"))
        );

        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma")), menu));
        add(new H2("Update Box"));

        Button cancel = new Button("Cancel");
        cancel.addClickListener(e ->
                cancel.getUI().ifPresent(ui ->
                        ui.navigate("updateBox"))
        );

        Button save = new Button("Save");
        String temporary = getBoxEdit().getId();

        TextField boxId = new TextField("ID");
        boxId.setClearButtonVisible(true);
        boxId.setPattern("[0-9]{5}");
        boxId.setHelperText("Format: 00000");
        boxId.setValue(getBoxEdit().getId());
        boxId.setEnabled(false);
        ComboBox<BoxSize> boxSizeComboBox = new ComboBox<>("Size Box: ", BoxSize.values());
        boxSizeComboBox.setValue(getBoxEdit().getSize());
        TextField boxWeight = new TextField("Weight");
        Div weightPrefix = new Div();
        weightPrefix.setText("Kg");
        boxWeight.setSuffixComponent(weightPrefix);
        boxWeight.setValue("0,1");
        boxWeight.setPattern("[0-9]{1,3}([,]{1}[0-9]{1})?");
        boxWeight.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        boxWeight.setHelperText("Format: 12 or 12,3");
        boxWeight.setValue(getBoxEdit().getWeight());
        TextField boxRecipient = new TextField("Recipient");
        boxRecipient.setPattern("[A-Z]{1}[a-z]{2,30}[ ]{1}[A-Z]{1}[a-z]{2,30}([-]{1}[A-Z]{1}[a-z]{2,30})*");
        boxRecipient.setHelperText("Format: Name Surname");
        boxRecipient.setValue(getBoxEdit().getRecipient());
        TextField boxSender = new TextField("Sender");
        boxSender.setPattern("[A-Z]{1}[a-z]{2,30}[ ]{1}[A-Z]{1}[a-z]{2,30}([-]{1}[A-Z]{1}[a-z]{2,30})*");
        boxSender.setHelperText("Format: Name Surname");
        boxSender.setValue(getBoxEdit().getSender());

        ComboBox<ParcelLocker> recipientParcelLockerComboBox = new ComboBox<>("Parcel Locker recipient", parcelLockerList.getParcelLockers());
        recipientParcelLockerComboBox.setItems(parcelLockerList.getParcelLockers());
        recipientParcelLockerComboBox.setItemLabelGenerator(ParcelLocker::getName);
        recipientParcelLockerComboBox.setPlaceholder(parcelLockerList.getParcelLockers().get(0).getName());
        recipientParcelLockerComboBox.setValue(getBoxEdit().getRecipientParcel());

        ComboBox<ParcelLocker> senderParcelLockerComboBox = new ComboBox<>("Parcel Locker sender", parcelLockerList.getParcelLockers());
        senderParcelLockerComboBox.setItems(parcelLockerList.getParcelLockers());
        senderParcelLockerComboBox.setItemLabelGenerator(ParcelLocker::getName);
        senderParcelLockerComboBox.setPlaceholder(parcelLockerList.getParcelLockers().get(0).getName());
        senderParcelLockerComboBox.setValue(getBoxEdit().getSenderParcel());

        add(new H3("Box Data"));
        add(new HorizontalLayout(boxId, boxSizeComboBox, boxWeight));
        add(new H3("Personal Data"));
        add(new HorizontalLayout(boxRecipient, boxSender));
        add(new H3("Parcel Locker Data"));
        add(new HorizontalLayout(recipientParcelLockerComboBox, senderParcelLockerComboBox));

        save.addClickListener(e -> {

            if (boxId.isInvalid() || boxWeight.isInvalid() || boxRecipient.isInvalid() ||
                    boxSender.isInvalid() || boxId.getValue().equals("") ||
                    boxWeight.getValue().equals("") || boxRecipient.getValue().equals("") || boxSender.getValue().equals("")) {
                Notification notification = new Notification(
                        "Wrong Data", 3000);
                notification.open();

            } else if (checkIdBox(boxId.getValue(), boxList.getBox())) {

                Notification notification = new Notification(
                        "Parcel Locker with that ID or name already exists", 3000);
                notification.open();

            } else {


                for(Box box : boxList.getBox()){
                    if(box.)
                }





            }

        });

    }
}
