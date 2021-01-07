package sample.UI.scenes;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Model.Customer;
import sample.Model.Order;
import sample.Model.OrderItem;
import sample.Model.Type;
import sample.UI.dialogs.LoginDialog.ConfirmOrderDialog;
import sample.UI.dialogs.LoginDialog.CustomerDialog;
import sample.UI.dialogs.LoginDialog.GuitarDialog;
import sample.logic.OrderService;

public class OrdersScene {
    private Scene scene;
    private OrderService orderService;
    private Order order;

    TableView<OrderItem> ordersTableView = new TableView<>();
    Button btnAddArticle = new Button("Add Guitar ***");
    Button btnDelete = new Button("Delete");
    Button btnConfirm = new Button("Confirm");
    Button btnReset = new Button("Reset");
    Button btnSearch = new Button("Search Customer");
    Label lblWarning = new Label("");
    Label labelName = new Label("");
    Label labelLastName = new Label("");
    Label labelStreet = new Label("");
    Label labelPhone = new Label("");
    Label labelCity = new Label("");
    Label labelEmail = new Label("");
    public Scene getScene() {
        return scene;
    }

    public OrdersScene() {
        orderService = new OrderService();
        order= new Order();
        order.setOrderID(orderService.getLastOrderId());
        //*********************** new Method
        HBox hBoxBtns = new HBox();
        hBoxBtns.getChildren().addAll(btnAddArticle, btnDelete, btnConfirm, btnReset, btnSearch);

        GridPane customerPane = makeCustomerDetails();
        VBox vboxTop = new VBox();
        HBox topHbox = new HBox();
        // add tableColumn
        makeTableColumns();
        topHbox.getChildren().addAll(vboxTop, customerPane);
        VBox parentVbox = new VBox();
        parentVbox.getChildren().addAll(topHbox, ordersTableView, hBoxBtns, lblWarning);

        BorderPane layout = new BorderPane();
        layout.setCenter(parentVbox);
        setButtonActions();

        scene = new Scene(layout);
    }
    private void restOrder() {
        // set the order to new
        this.order = new Order();
        // reset the labels
        this.labelPhone.setText("");
        this.labelStreet.setText("");
        this.labelEmail.setText("");
        this.labelLastName.setText("");
        this.labelName.setText("");
        this.labelCity.setText("");
        this.lblWarning.setText("");
        // reset the table view
        this.ordersTableView.getItems().clear();
    }
    private void showCustomerDetails(Customer customer) {
        labelName.setText(customer.getFirstName());
        labelLastName.setText(customer.getLastName());
        labelCity.setText(customer.getCity());
        labelEmail.setText(customer.getEmailAddress());
        labelStreet.setText(customer.getStreetAddress());
        labelPhone.setText(customer.getPhone());
    }
    private void setButtonActions() {

        btnAddArticle.setOnAction(
                a -> {
                    GuitarDialog guitarDialog = new GuitarDialog(order.getOrderItems());
                    guitarDialog.getGuitarStage().show();
                    if (order.getOrderItems().size() != 0) // make sure the window was not closed or canceled
                    {
                        this.ordersTableView.getItems().clear(); // to make sure every time there is a clear list of order items
                        this.ordersTableView.setItems(FXCollections.observableArrayList(order.getOrderItems()));
                    }
                });

        btnDelete.setOnAction(
                actionEvent -> {
                    if (ordersTableView.getSelectionModel().getSelectedItem() == null) {
                        return;
                    }
                    OrderItem orderItem = ordersTableView.getSelectionModel().getSelectedItem();
                    order.getOrderItems().remove(orderItem);
                    ordersTableView.setItems(FXCollections.observableArrayList(order.getOrderItems()));
                });

        // the search text was removed because I understand that it was there to assess if I can send a
        // string to
        // another window, but I am doing that in other places alot
        // so instead of the search text field I made a nice live filter on the other window :)
        btnSearch.setOnAction(
                c -> {
                    CustomerDialog cw = new CustomerDialog();
                    cw.getStage().showAndWait();

                    if (cw.getCustomer() != null) {
                        order.setCustomer(cw.getCustomer());
                        showCustomerDetails(order.getCustomer());
                    }
                });

        btnReset.setOnAction(
                a -> {
                    restOrder();
                });

        btnConfirm.setOnAction(
                d -> {
                    if (order.getCustomer() != null && order.getOrderItems().size() != 0) {
                        ConfirmOrderDialog confirmationWindow = new ConfirmOrderDialog(order);
                        confirmationWindow.getStage().showAndWait();
                        if (confirmationWindow.isConfirmed()) {
                            lblWarning.setText("Order was successfully placed.");
                        }
                        restOrder();
                    } else if (order.getCustomer() == null
                            && order.getOrderItems().size()
                            == 0) { // cuz customer itself wont be null, it is being created in order ctor
                        lblWarning.setText("Atleast select something :|");

                    } else if (order.getOrderItems().size() == 0) {
                        lblWarning.setText("select articles please");
                    } else if (order.getCustomer() == null) {
                        lblWarning.setText("select a customer please");
                    }
                });

    }
    private GridPane makeCustomerDetails() {
        Label lblFirstName = new Label("First Name");
        Label lblLastName = new Label("Last Name");
        Label lblStreet = new Label("Street Address");
        Label lblPhone = new Label("Phone Number");
        Label lblCity = new Label("City");
        Label lblEmail = new Label("Email Address");

        GridPane customerGridPane = new GridPane();
        customerGridPane.setPadding(new Insets(20));

        GridPane.setConstraints(lblFirstName, 0, 0);
        GridPane.setConstraints(lblStreet, 0, 1);
        GridPane.setConstraints(lblPhone, 0, 2);
        GridPane.setConstraints(labelName, 1, 0);
        GridPane.setConstraints(labelStreet, 1, 1);
        GridPane.setConstraints(labelPhone, 1, 2);
        GridPane.setConstraints(lblLastName, 2, 0);
        GridPane.setConstraints(lblCity, 2, 1);
        GridPane.setConstraints(lblEmail, 2, 2);
        GridPane.setConstraints(labelLastName, 3, 0);
        GridPane.setConstraints(labelCity, 3, 1);
        GridPane.setConstraints(labelEmail, 3, 2);

        customerGridPane
                .getChildren()
                .addAll(
                        lblFirstName,
                        lblStreet,
                        lblPhone,
                        labelName,
                        labelStreet,
                        labelPhone,
                        lblLastName,
                        lblCity,
                        lblEmail,
                        labelLastName,
                        labelCity,
                        labelEmail);

        customerGridPane.getStyleClass().add("customerGridpane");

        return customerGridPane;
    }

    private void makeTableColumns() {

        TableColumn<OrderItem, Integer> colQty = new TableColumn<>("Quantity");
        colQty.setMinWidth(150);
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<OrderItem, String> colBrand = new TableColumn<>("Brand");
        colBrand.setMinWidth(150);
        colBrand.setCellValueFactory(
                row -> new SimpleStringProperty(row.getValue().getGuitar().getBrand()));

        TableColumn<OrderItem, String> colModel = new TableColumn<>("Model");
        colModel.setMinWidth(150);
        colModel.setCellValueFactory(
                row -> new SimpleStringProperty(row.getValue().getGuitar().getModel()));

        TableColumn<OrderItem, Boolean> colAcoustic = new TableColumn<>("Acoustic");
        colAcoustic.setMinWidth(150);
        colAcoustic.setCellValueFactory(
                row -> new SimpleObjectProperty<>(row.getValue().getGuitar().isAcoustic()));

        TableColumn<OrderItem, Type> colType = new TableColumn<>("Type");
        colType.setMinWidth(150);
        colType.setCellValueFactory(
                row -> new SimpleObjectProperty<>(row.getValue().getGuitar().getType()));

        TableColumn<OrderItem, Double> colPrice = new TableColumn<>("Price");
        colPrice.setMinWidth(150);
        colPrice.setCellValueFactory(
                row -> new SimpleObjectProperty<>(row.getValue().getGuitar().getPrice()));

        ordersTableView.getColumns().addAll(colQty, colBrand, colModel, colAcoustic, colType, colPrice);
    }
}
