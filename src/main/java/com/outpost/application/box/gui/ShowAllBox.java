package com.outpost.application.box.gui;

import com.outpost.application.box.Box;
import com.outpost.application.box.BoxList;
import com.outpost.application.parcellocker.ParcelLocker;
import com.outpost.application.parcellocker.ParcelLockerList;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
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

@Route("showAllBox")
public class ShowAllBox extends VerticalLayout {

    @Autowired
    public ShowAllBox(BoxList boxList) {

        Button menu = new Button("Menu");
        menu.addClickListener(e -> menu.getUI().ifPresent(ui -> ui.navigate("hello")));

        add(new HorizontalLayout((new Image("https://i.postimg.cc/rmNmhchD/Out-Post-Logo.png", "nie ma")), menu));
        add(new H2("Show All Parcel Locker"));

        Grid<Box> grid = new Grid<>(Box.class, false);
        grid.setColumnReorderingAllowed(true);
        Grid.Column<Box> id = grid.addColumn(Box::getId).setHeader("Box ID").setResizable(true).setSortable(true);
        Grid.Column<Box> size = grid.addColumn(Box::getSize).setHeader("Box Size").setResizable(true).setSortable(true);
        Grid.Column<Box> weight = grid.addColumn(Box::getWeight).setHeader("Box Weight").setResizable(true).setSortable(true);
        Grid.Column<Box> recipient = grid.addColumn(Box::getRecipient).setHeader("Box Recipient").setResizable(true).setSortable(true);
        Grid.Column<Box> sender = grid.addColumn(Box::getSender).setHeader("Box Sender").setResizable(true).setSortable(true);
        Grid.Column<Box> parcelLockerRecipient = grid.addColumn(box -> box.getRecipientParcel().getName()).setHeader("Parcel Locker Recipient").setResizable(true).setSortable(true);
        Grid.Column<Box> parcelLockerSender = grid.addColumn(box -> box.getSenderParcel().getName()).setHeader("Parcel Locker Sender").setResizable(true).setSortable(true);
        Grid.Column<Box> status = grid.addColumn(Box::getBoxStatus).setHeader("Box Status").setResizable(true).setSortable(true);

        List<Box> box = boxList.getBox();
        ListDataProvider<Box> dataProvider = new ListDataProvider<>(box);
        grid.setDataProvider(dataProvider);

        TextField searchField = new TextField();
        searchField.setWidth("50%");
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.addValueChangeListener(e -> dataProvider.refreshAll());

        dataProvider.addFilter(box1 -> {
            String searchTerm = searchField.getValue().trim();

            if (searchTerm.isEmpty())
                return true;

            boolean matchesId = matchesTerm(box1.getId(), searchTerm);
            boolean matchesSize = matchesTerm(box1.getSize().toString(), searchTerm);
            boolean matchesWeight = matchesTerm(box1.getWeight(), searchTerm);
            boolean matchesRecipient = matchesTerm(box1.getRecipient(), searchTerm);
            boolean matchesSender = matchesTerm(box1.getSender(), searchTerm);
            boolean matchesParcelRecipient = matchesTerm(box1.getRecipientParcel().getName(), searchTerm);
            boolean matchesParcelSender = matchesTerm(box1.getSenderParcel().getName(), searchTerm);
            boolean matchesStatus = matchesTerm(box1.getBoxStatus().toString(), searchTerm);


            return matchesId || matchesSize || matchesWeight || matchesRecipient || matchesSender ||
                    matchesParcelRecipient||matchesParcelSender || matchesStatus ;
        });

        VerticalLayout layout = new VerticalLayout(searchField, grid);
        layout.setPadding(false);

        add(layout);
    }
    private boolean matchesTerm(String value, String searchTerm) {
        return value.toLowerCase().contains(searchTerm.toLowerCase());
    }}