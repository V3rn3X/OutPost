package com.outpost.application.box.gui;


import com.outpost.application.box.Box;
import com.outpost.application.box.BoxList;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("deleteBox")
public class DeleteBox extends VerticalLayout  {

    String toDelete = "";

    public DeleteBox(BoxList boxList) {


        Button menu = new Button("Menu");
        menu.addClickListener(e ->
                menu.getUI().ifPresent(ui ->
                        ui.navigate("hello"))
        );

        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma")), menu));

        add(new H2("Delete Box"));

        Button deleteBox = new Button("Delete");

        ComboBox<Box> boxComboBox = new ComboBox<>("Choose Box", boxList.getBox());
        boxComboBox.setItems(boxList.getBox());
        boxComboBox.setItemLabelGenerator(Box::getId);
        boxComboBox.setPlaceholder(boxList.getBox().get(0).getId());
        boxComboBox.setValue(boxList.getBox().get(0));

        deleteBox.addClickListener(clickEvent -> {

            toDelete = boxComboBox.getValue().getId();
            boxList.deleteBox(toDelete);

            if (boxList.getBox().size() == 0) {
                Notification notification1 = new Notification(
                        "You are delete all Parcel Locker", 6000);
                notification1.open();
                menu.getUI().ifPresent(ui ->
                        ui.navigate("hello"));
            } else {
                UI.getCurrent().getPage().reload();
            }
        });
        add(boxComboBox, deleteBox);




    }
}
