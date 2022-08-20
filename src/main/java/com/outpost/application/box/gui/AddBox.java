package com.outpost.application.box.gui;

import com.outpost.application.box.Box;
import com.outpost.application.box.BoxList;
import com.outpost.application.box.BoxSize;
import com.outpost.application.box.BoxStatus;
import com.outpost.application.parcellocker.ParcelLocker;
import com.outpost.application.parcellocker.ParcelLockerList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import static com.outpost.application.box.BoxList.checkIdBox;

@Route("addBox")
public class AddBox extends VerticalLayout {

    @Autowired
    public AddBox(BoxList boxList, ParcelLockerList parcelLockerList) {

        Button menu = new Button("Menu");
        menu.addClickListener(e ->
                menu.getUI().ifPresent(ui ->
                        ui.navigate("hello"))
        );

        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma")), menu));
        add(new H2("Add Box"));

        TextField boxId = new TextField("ID");
        boxId.setClearButtonVisible(true);
        boxId.setPattern("[0-9]{5}");
        boxId.setHelperText("Format: 00000");
        ComboBox<BoxSize> boxSizeComboBox = new ComboBox<>("Size Box: ", BoxSize.values());
        boxSizeComboBox.setValue(BoxSize.SMALL);
        TextField boxWeight = new TextField("Weight");
        Div weightPrefix = new Div();
        weightPrefix.setText("Kg");
        boxWeight.setSuffixComponent(weightPrefix);
        boxWeight.setValue("0,1");
        boxWeight.setPattern("[0-9]{1,3}([,]{1}[0-9]{1})?");
        boxWeight.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
        boxWeight.setHelperText("Format: 12 or 12,3");
        TextField boxRecipient = new TextField("Recipient");
        boxRecipient.setPattern("[A-Z]{1}[a-z]{2,30}[ ]{1}[A-Z]{1}[a-z]{2,30}([-]{1}[A-Z]{1}[a-z]{2,30})*");
        boxRecipient.setHelperText("Format: Name Surname");
        TextField boxSender = new TextField("Sender");
        boxSender.setPattern("[A-Z]{1}[a-z]{2,30}[ ]{1}[A-Z]{1}[a-z]{2,30}([-]{1}[A-Z]{1}[a-z]{2,30})*");
        boxSender.setHelperText("Format: Name Surname");

        ComboBox<ParcelLocker> recipientParcelLockerComboBox = new ComboBox<>("Parcel Locker recipient", parcelLockerList.getParcelLockers());
        recipientParcelLockerComboBox.setItems(parcelLockerList.getParcelLockers());
        recipientParcelLockerComboBox.setItemLabelGenerator(ParcelLocker::getName);
        recipientParcelLockerComboBox.setPlaceholder(parcelLockerList.getParcelLockers().get(0).getName());
        recipientParcelLockerComboBox.setValue(parcelLockerList.getParcelLockers().get(0));

        ComboBox<ParcelLocker> senderParcelLockerComboBox = new ComboBox<>("Parcel Locker sender", parcelLockerList.getParcelLockers());
        senderParcelLockerComboBox.setItems(parcelLockerList.getParcelLockers());
        senderParcelLockerComboBox.setItemLabelGenerator(ParcelLocker::getName);
        senderParcelLockerComboBox.setPlaceholder(parcelLockerList.getParcelLockers().get(0).getName());
        senderParcelLockerComboBox.setValue(parcelLockerList.getParcelLockers().get(0));

        ComboBox<BoxStatus> boxStatusComboBox = new ComboBox<>("Status: ", BoxStatus.values());
        boxStatusComboBox.setValue(BoxStatus.CREATING);
        boxStatusComboBox.setEnabled(false);

        add(new H3("Box Data"));
        add(new HorizontalLayout(boxId, boxSizeComboBox, boxWeight));
        add(new H3("Personal Data"));
        add(new HorizontalLayout(boxRecipient, boxSender));
        add(new H3("Parcel Locker Data"));
        add(new HorizontalLayout(recipientParcelLockerComboBox, senderParcelLockerComboBox, boxStatusComboBox));

        Button addBox = new Button("---- Add Box ----");
        Button verify = new Button("Check");
        Button cancel = new Button("Cancel");
        cancel.setEnabled(false);
        addBox.setEnabled(false);


        verify.addClickListener(clickEvent -> {

            if (boxId.isInvalid() || boxWeight.isInvalid() || boxRecipient.isInvalid() ||
                    boxSender.isInvalid() || boxId.getValue().equals("") ||
                    boxWeight.getValue().equals("") || boxRecipient.getValue().equals("") || boxSender.getValue().equals("")) {
                Notification notification = new Notification(
                        "Wrong Data", 3000);
                notification.open();
            } else if (checkIdBox(boxId.getValue(), boxList.getBox())) {

                Notification notification = new Notification(
                        "Box with that ID already exists", 3000);
                notification.open();
            } else {
                boxId.setEnabled(false);
                boxWeight.setEnabled(false);
                boxSizeComboBox.setEnabled(false);
                boxRecipient.setEnabled(false);
                boxSender.setEnabled(false);
                recipientParcelLockerComboBox.setEnabled(false);
                senderParcelLockerComboBox.setEnabled(false);
                addBox.setEnabled(true);
                cancel.setEnabled(true);
                verify.setEnabled(false);
            }
        });

        cancel.addClickListener(clickEvent -> {
            boxId.setEnabled(true);
            boxWeight.setEnabled(true);
            boxSizeComboBox.setEnabled(true);
            boxRecipient.setEnabled(true);
            boxSender.setEnabled(true);
            recipientParcelLockerComboBox.setEnabled(true);
            senderParcelLockerComboBox.setEnabled(true);
            addBox.setEnabled(false);
            cancel.setEnabled(false);
            verify.setEnabled(true);
        });

        addBox.addClickListener(clickEvent -> {
            Box box = new Box();
            box.setId(boxId.getValue());
            box.setSize(boxSizeComboBox.getValue());
            box.setWeight(boxWeight.getValue());
            box.setRecipient(boxRecipient.getValue());
            box.setSender(boxSender.getValue());
            box.setRecipientParcel(recipientParcelLockerComboBox.getValue());
            box.setSenderParcel(senderParcelLockerComboBox.getValue());
            box.setBoxStatus(boxStatusComboBox.getValue());
            boxList.getBox().add(box);

            Notification notification = new Notification(
                    "Box added", 3000);
            notification.open();

            boxId.setEnabled(true);
            boxWeight.setEnabled(true);
            boxSizeComboBox.setEnabled(true);
            boxRecipient.setEnabled(true);
            boxSender.setEnabled(true);
            recipientParcelLockerComboBox.setEnabled(true);
            senderParcelLockerComboBox.setEnabled(true);
            addBox.setEnabled(false);
            cancel.setEnabled(false);
            verify.setEnabled(true);
        });

        add(new HorizontalLayout(addBox));
        add(new HorizontalLayout(verify, cancel));

    }
}
