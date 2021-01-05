package sample.UI.scenes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.DAO.DataBase;
import sample.Model.Guitar;

public class GuitarListScene {
    private Scene scene;
    private DataBase db;

    Label heading;
    //TableView<Guitar> GuitarTableView;
    TableView<Guitar> GuitarTableView = new TableView<>();
    public Scene getScene() { return scene; }

    public GuitarListScene() {
        db = new DataBase();

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(20);
        Label heading = new Label();
        heading.setText("Guitar list Scene");
        heading.getStyleClass().add("header");

        MakeTableView();

        HBox GuitarMenu = new HBox();
        GuitarMenu.setPadding(new Insets(20, 0, 0, 0));
        GuitarMenu.setSpacing(10);

        Button addGuitarButton = new Button("Add Guitar");
        GuitarMenu.getChildren().addAll(addGuitarButton);

        layout.getChildren().addAll(heading, GuitarTableView, GuitarMenu);
        scene = new Scene(layout);
    }
    void MakeTableView()
    {
        TableColumn uuiCol = new TableColumn<>("GuitarID");
        uuiCol.setMinWidth(100);
        uuiCol.setCellValueFactory(new PropertyValueFactory<Guitar, String>("GuitarID"));

        TableColumn modelCol = new TableColumn<>("model");
        modelCol.setMinWidth(100);
        modelCol.setCellValueFactory(new PropertyValueFactory<Guitar, String>("model"));

        TableColumn brandCol = new TableColumn<>("brand");
        brandCol.setMinWidth(100);
        brandCol.setCellValueFactory(new PropertyValueFactory<Guitar, String>("brand"));

        TableColumn typeCol = new TableColumn<>("type");
        typeCol.setMinWidth(130);
        typeCol.setCellValueFactory(new PropertyValueFactory<Guitar, Enum>("type"));

        TableColumn priceCol = new TableColumn<>("price");
        priceCol.setMinWidth(130);
        priceCol.setCellValueFactory(new PropertyValueFactory<Guitar, Double>("price"));

        TableColumn acousticCol = new TableColumn<>("acoustic");
        acousticCol.setMinWidth(130);
        acousticCol.setCellValueFactory(new PropertyValueFactory<Guitar, Boolean>("acoustic"));

        TableColumn quantitycCol = new TableColumn<>("quantity");
        quantitycCol.setMinWidth(130);
        quantitycCol.setCellValueFactory(new PropertyValueFactory<Guitar, Integer>("quantity"));

        this.GuitarTableView.getColumns().addAll(uuiCol, modelCol, brandCol, typeCol, priceCol, acousticCol, quantitycCol);
        this.GuitarTableView.setItems(FXCollections.observableArrayList(db.getGuitars()));
    }


}
