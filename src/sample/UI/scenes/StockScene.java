package sample.UI.scenes;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Model.Guitar;
import sample.logic.GuitarService;


public class StockScene {

    GuitarService guitarService = new GuitarService();
    Label selectedGuitar = new Label();
    CheckBox chbNegate = new CheckBox();
    private final Scene scene;
    private final TableView<Guitar> GuitarTableView = new TableView<>();
    private Guitar articleToChange;

    public StockScene() {

        chbNegate.setText("Negate");
        Label stockMaintenance = new Label("Stock maintenance");
        Label lblWarning = new Label("");
        TextField txtQuantity = new TextField();
        MakeTableView();
        txtQuantity.setPromptText("enter quantity to change");
        txtQuantity
                .textProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            if (GuitarTableView.getSelectionModel().getSelectedItem() == null) {
                                lblWarning.setText("no item selected");
                                return;
                            }
                            if (newValue.isEmpty() || Integer.parseInt(newValue) <= articleToChange.getQuantity()) {
                                lblWarning.setText("");

                            } else if (articleToChange.getQuantity() - (Integer.parseInt(newValue)) < 0) {
                                lblWarning.setText("you are going minus!!!");
                            }

                        });

        GuitarTableView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            if (newValue != null) {
                articleToChange = newValue;
                txtQuantity.setText(String.format("%s", articleToChange.getQuantity()));
            }
        }));

        Button btnConfirm = new Button("Confirm");
        btnConfirm.setOnAction(
                a -> {
                    if (txtQuantity.getText().isEmpty()
                            || Integer.parseInt(txtQuantity.getText()) == 0
                            || GuitarTableView.getSelectionModel().getSelectedItem() == null) {
                        lblWarning.setText("select a valid amount");
                        return;
                    }else
                    {
                        // the stock is deducted but we cannot see it if we reload the data as is not in database
                    guitarService.reduceStockAmount(Integer.parseInt(txtQuantity.getText()), articleToChange, chbNegate.isSelected());
                    }
                });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(txtQuantity, chbNegate, selectedGuitar ,btnConfirm);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(stockMaintenance, GuitarTableView, hBox, lblWarning);

        BorderPane layout = new BorderPane();
        layout.setCenter(vBox);
        layout.setBottom(hBox);
        scene = new Scene(layout);
    }

    public Scene getScene() {
        return scene;
    }

    private void MakeTableView() {
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
        this.GuitarTableView.getColumns().addAll(quantitycCol, modelCol, brandCol, typeCol, priceCol, acousticCol,uuiCol);
        this.GuitarTableView.setItems(FXCollections.observableArrayList(guitarService.getAllGuitars()));
        GetSelectedGuitar();
    }

    private void GetSelectedGuitar() {
        GuitarTableView.setRowFactory(
                a -> {
                    TableRow<Guitar> rowArticle = new TableRow<>();
                    rowArticle.setOnMouseClicked(
                            b -> {
                                if (b.getClickCount() == 1 && !rowArticle.isEmpty()) {
                                    articleToChange = rowArticle.getItem();
                                    selectedGuitar.setText("You have selected :"+ articleToChange.getModel());
                                }
                            });

                    return rowArticle;
                });
    }


}
