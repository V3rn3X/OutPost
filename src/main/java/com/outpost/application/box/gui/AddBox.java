package com.outpost.application.box.gui;


import com.outpost.application.box.BoxList;
import com.outpost.application.box.BoxSize;
import com.outpost.application.box.BoxStatus;
import com.outpost.application.parcellocker.ParcelLocker;
import com.outpost.application.parcellocker.ParcelLockerList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;

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

        TextField boxStatus = new TextField("Status");
        boxStatus.setValue("CREATING");
        boxStatus.setEnabled(false);

        add(new H3("Box Data"));
        add(new HorizontalLayout(boxId, boxSizeComboBox, boxWeight));
        add(new H3("Personal Data"));
        add(new HorizontalLayout(boxRecipient, boxSender));
        add(new H3("Parcel Locker Data"));
        add(new HorizontalLayout(recipientParcelLockerComboBox, senderParcelLockerComboBox, boxStatus));

    }
}
