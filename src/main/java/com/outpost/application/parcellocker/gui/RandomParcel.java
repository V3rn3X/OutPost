package com.outpost.application.parcellocker.gui;

import com.outpost.application.parcellocker.ParcelLocker;
import com.outpost.application.parcellocker.ParcelLockerList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.Random;

@Route("RandomParcel")
public class RandomParcel extends VerticalLayout {

    public RandomParcel(ParcelLockerList parcelLockerList) {

        Button menu = new Button("Menu");
        menu.addClickListener(e -> menu.getUI().ifPresent(ui -> ui.navigate("hello")));

        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma")), menu));
        add(new H2("Random Parcel"));

        Button RandomParcel = new Button("Add Random Parcel Locker");
        add(RandomParcel);

        String[] city = {"Warszawa", "Kraków", "Katowice", "Gdańsk", "Wrocław", "Szczecin"};
        String[] street = {"Długa", "Krótka", "Czechowa", "Jan Pawła II", "Warszawska", "Poniatowskiego", "Cmentarna", "Alpejska"};
        String[] zipcode = {"01-000", "23-435", "65-234", "43-654", "64-112", "97-452"};

        RandomParcel.addClickListener(clickEvent -> {
            Random rand = new Random();
            int n = rand.nextInt(6);
            int m = rand.nextInt(8);
            int o = rand.nextInt(100);
                    ParcelLocker parcelLocker1 = new ParcelLocker();
                    parcelLocker1.setID(n + m + " ");
                    parcelLocker1.setName("Testowy" + o);
                    parcelLocker1.setStreet(street[m]);
                    parcelLocker1.setCity(city[n]);
                    parcelLocker1.setZipcode(zipcode[n]);
                    parcelLockerList.getParcelLockers().add(parcelLocker1);

                }

        );

    }
}
