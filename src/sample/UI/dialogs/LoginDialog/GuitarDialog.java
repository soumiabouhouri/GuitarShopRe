package sample.UI.dialogs.LoginDialog;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Model.Guitar;
import sample.Model.OrderItem;
import sample.UI.scenes.OrdersScene;
import sample.logic.GuitarService;
import sample.logic.OrderService;

import java.util.List;

public class GuitarDialog {
    private OrdersScene  ordersScene;
    private OrderService orderService;


    private GuitarService guitarService;
    private List<OrderItem> orderItems;
    private TextField txtQuantity = new TextField();
    private Button btnAdd = new Button("Add this");
    private Button btnConfirm = new Button("Confirm");
    private TableView<Guitar> GuitarTableView = new TableView<>();
    private Label lblArticles = new Label("Select an Article");
    private Label lblWarning = new Label("");
    private Label orderItemsLabel = new Label();

    private HBox hBoxButtonsLayout;

    private VBox mainVbox;
    private Stage guitarStage;
    public Stage getGuitarStage() {
        return guitarStage;
    }

    public GuitarDialog(List<OrderItem> orderItems) {
        ordersScene= new OrdersScene();
        guitarService = new GuitarService();
        orderService= new OrderService();
        this.guitarStage = new Stage();
        this.guitarStage.setResizable(false);

        this.guitarStage.setTitle("Guitar shop FX - Add Article");
        this.guitarStage.initModality(Modality.APPLICATION_MODAL);
        this.orderItems = orderItems;
        guitarStage.setOnCloseRequest(actionEvent -> {});
        makeArticleScene();
    }

    private void makeArticleScene() {

        makeTableViewColumns(GuitarTableView);
        makeTextBox();
        buttonActions();

        hBoxButtonsLayout = new HBox();
        hBoxButtonsLayout.getChildren().addAll(txtQuantity, btnAdd, btnConfirm, orderItemsLabel);

        mainVbox = new VBox();
        mainVbox.getChildren().addAll(lblArticles, GuitarTableView, hBoxButtonsLayout, lblWarning);

        BorderPane layout = new BorderPane();
        layout.setCenter(mainVbox);
        Scene articleScene =  new Scene(layout);

        this.guitarStage.setScene(articleScene);
    }
    private void makeTextBox() {
        txtQuantity.setPromptText("enter quantity");
        txtQuantity
                .textProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            if (GuitarTableView.getSelectionModel().getSelectedItem() == null) {
                                lblWarning.setText("no item selected");
                                return;
                            }
                            Guitar article = GuitarTableView.getSelectionModel().selectedItemProperty().getValue();
                            if (newValue.isEmpty() || Integer.parseInt(newValue) <= article.getQuantity()) {

                                lblWarning.setText("");
                                btnAdd.setDisable(false);

                            } else if (Integer.parseInt(newValue) > article.getQuantity()) {
                                lblWarning.setText(
                                        String.format(
                                                "not enough in stock for %s. only %s remaining",
                                                article.getModel(), article.getQuantity()));
                                btnAdd.setDisable(true);
                            }
                        });
    }
    private void buttonActions() {
        btnAdd.setOnAction(
                a -> {
                    if (GuitarTableView.getSelectionModel().getSelectedItem() == null) {
                        lblWarning.setText("no item selected");
                        return;
                    }
                    if (txtQuantity.getText().isEmpty() || Integer.parseInt(txtQuantity.getText()) == 0) {
                        lblWarning.setText("please select a valid quantity");
                        return;
                    }
                    Guitar guitar = GuitarTableView.getSelectionModel().selectedItemProperty().getValue();

                    OrderItem orderItem = new OrderItem(guitar, Integer.parseInt(txtQuantity.getText()));
                    boolean existing = false;
                    for (OrderItem o : orderItems) {
                        if (o.getGuitar().getGuitarID() == guitar.getGuitarID()) {
                            o.setQuantity(o.getQuantity() + Integer.parseInt(txtQuantity.getText()));
                            existing = true;
                            break;
                        }
                    }
                    if (!existing) {
                        orderItems.add(orderItem);
                    }
                    orderItemsLabel.setText(
                            orderItemsLabel.getText()
                                    + orderItem.getGuitar().getModel()
                                    + " "
                                    + orderItem.getQuantity()
                                    + "\n"); // because its a good thing to know what you already have in your list,
                    // right?

                });

        btnConfirm.setOnAction(
                a -> {
                    if (this.orderItems.size() != 0) { }
                    this.guitarStage.close();
                });
    }
    private void makeTableViewColumns(TableView<Guitar> GuitarTableView) {
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

        GuitarTableView.getColumns().addAll(uuiCol, modelCol, brandCol, typeCol, priceCol, acousticCol, quantitycCol);
        GuitarTableView.setItems(FXCollections.observableArrayList(guitarService.getAllGuitars()));
    }
}
