package com.outpost.application.box.gui;

import com.outpost.application.box.Box;
import com.outpost.application.box.BoxList;
import com.outpost.application.parcellocker.ParcelLocker;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import static com.outpost.application.box.EditBox.setBoxEdit;
import static com.outpost.application.parcellocker.EditParcelLocker.setParcelLockerEdit;

@Route("updateBox")
public class UpdateBox  extends VerticalLayout {

    public UpdateBox(BoxList boxList){

        Button menu = new Button("Menu");
        menu.addClickListener(e ->
                menu.getUI().ifPresent(ui ->
                        ui.navigate("hello"))
        );

        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma")), menu));
        add(new H2("Update Box"));


      Button updateBox = new Button("Edit");

        ComboBox<Box> boxComboBox1 = new ComboBox<>("Choose Box", boxList.getBox());
        boxComboBox1.setItems(boxList.getBox());
        boxComboBox1.setItemLabelGenerator(Box::getId);
        boxComboBox1.setValue(boxList.getBox().get(0));


        updateBox.addClickListener(e -> {

            setBoxEdit(boxComboBox1.getValue());

            updateBox.getUI().ifPresent(ui ->
                    ui.navigate("updateBox2"));
        });



        add(boxComboBox1, updateBox);



    }
}
