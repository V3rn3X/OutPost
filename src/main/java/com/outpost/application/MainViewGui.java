package com.outpost.application;


import com.outpost.application.parcellocker.ParcelLockerList;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;


@Route("hello")
public class MainViewGui extends VerticalLayout {


    public MainViewGui(ParcelLockerList parcelLockerList) {


        add(new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "Error_1"));

        Button addParcelLocker = new Button("Add Parcel Locker");
        Button deleteParcelLocker = new Button("Delete Parcel Locker");
        Button showAllParcelLocker = new Button("Show All Parcel Locker");
        Button updateParcelLocker = new Button("Update Parcel Locker");
        Button addBox = new Button("Add Box");
        Button deleteBox = new Button("Delete Box");
        Button showAllBox = new Button("Show All Box");
        Button updateBox = new Button("Update Box");
        Button randomParcelLocker = new Button("Random Parcel Locker");

        add(new H2("Menu Parcel Locker"));
        add(new HorizontalLayout(addParcelLocker, deleteParcelLocker, showAllParcelLocker, updateParcelLocker));

        add(new H2("Menu Box"));
        add(new HorizontalLayout(addBox, deleteBox, showAllBox, updateBox));

        add(new H2("Random Parcel Locker"));
        add(new HorizontalLayout(randomParcelLocker));

        addParcelLocker.addClickListener(e ->
                addParcelLocker.getUI().ifPresent(ui ->
                        ui.navigate("addParcelLocker"))
        );

        showAllParcelLocker.addClickListener(e ->
                showAllParcelLocker.getUI().ifPresent(ui ->
                        ui.navigate("showAllParcelLocker"))
        );

        randomParcelLocker.addClickListener(e ->
                showAllParcelLocker.getUI().ifPresent(ui ->
                        ui.navigate("RandomParcel"))
        );

        deleteParcelLocker.addClickListener(e ->
                showAllParcelLocker.getUI().ifPresent(ui ->
                        ui.navigate("deleteParcelLocker"))
        );


        if(parcelLockerList.getParcelLockers().size() == 0)
        {
            updateParcelLocker.setEnabled(false);
            deleteParcelLocker.setEnabled(false);
        } else{
            updateParcelLocker.setEnabled(true);
            deleteParcelLocker.setEnabled(true);
        }

        updateParcelLocker.addClickListener(e ->
                showAllParcelLocker.getUI().ifPresent(ui ->
                        ui.navigate("updateParcelLocker"))
        );

        addBox.addClickListener(e ->
                addBox.getUI().ifPresent(ui ->
                        ui.navigate("addBox"))
        );




    }
}