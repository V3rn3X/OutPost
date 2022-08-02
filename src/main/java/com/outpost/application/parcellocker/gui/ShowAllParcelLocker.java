package com.outpost.application.parcellocker.gui;

import com.outpost.application.parcellocker.ParcelLocker;
import com.outpost.application.parcellocker.ParcelLockerList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Route("showAllParcelLocker")
public class ShowAllParcelLocker extends VerticalLayout {

    @Autowired
    public ShowAllParcelLocker(ParcelLockerList parcelLockerList) {

        Button menu = new Button("Menu");
        menu.addClickListener(e -> menu.getUI().ifPresent(ui -> ui.navigate("hello")));

        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma")), menu));
        add(new H2("Show All Parcel Locker"));


        Grid<ParcelLocker> grid = new Grid<>(ParcelLocker.class, false);
        grid.setColumnReorderingAllowed(true);
        Grid.Column<ParcelLocker> id = grid.addColumn(ParcelLocker::getID).setHeader("Parcel Locker ID").setResizable(true).setSortable(true);
        Grid.Column<ParcelLocker> name = grid.addColumn(ParcelLocker::getName).setHeader("Parcel Locker Name").setResizable(true).setSortable(true);
        Grid.Column<ParcelLocker> street = grid.addColumn(ParcelLocker::getStreet).setHeader("Street").setResizable(true).setSortable(true);
        Grid.Column<ParcelLocker> city = grid.addColumn(ParcelLocker::getCity).setHeader("City").setResizable(true).setSortable(true);
        Grid.Column<ParcelLocker> zipcode = grid.addColumn(ParcelLocker::getZipcode).setHeader("Zip Code").setResizable(true).setSortable(true);
        HeaderRow headerRow = grid.prependHeaderRow();
        headerRow.join(id, name).setText("Identification");
        headerRow.join(street, city, zipcode).setText("Address");

        List<ParcelLocker> parcelLockers = parcelLockerList.getParcelLockers();
        ListDataProvider<ParcelLocker> dataProvider = new ListDataProvider<>(parcelLockers);
        grid.setDataProvider(dataProvider);

        TextField searchField = new TextField();
        searchField.setWidth("50%");
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.addValueChangeListener(e -> dataProvider.refreshAll());

        dataProvider.addFilter(parcelLocker -> {
            String searchTerm = searchField.getValue().trim();

            if (searchTerm.isEmpty())
                return true;

            boolean matchesId = matchesTerm(parcelLocker.getID(), searchTerm);
            boolean matchesName = matchesTerm(parcelLocker.getName(), searchTerm);
            boolean matchesStreet = matchesTerm(parcelLocker.getStreet(), searchTerm);
            boolean matchesCity = matchesTerm(parcelLocker.getCity(), searchTerm);
            boolean matchesZipcode = matchesTerm(parcelLocker.getZipcode(), searchTerm);

            return matchesId || matchesName || matchesStreet || matchesCity || matchesZipcode;
        });

        VerticalLayout layout = new VerticalLayout(searchField, grid);
        layout.setPadding(false);

        add(layout);
    }
    private boolean matchesTerm(String value, String searchTerm) {
        return value.toLowerCase().contains(searchTerm.toLowerCase());
    }
}
