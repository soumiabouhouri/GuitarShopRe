package sample.UI.dialogs.LoginDialog;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Model.Order;
import sample.Model.OrderItem;
import sample.logic.OrderService;

import java.time.LocalDate;

public class ConfirmOrderDialog {
    private Stage stage;
    private Order order;
    private OrderService orderService;
    public boolean isConfirmed()
    {
        return confirmed;
    }
    boolean confirmed = false;

    public Stage getStage() {
        return stage;
    }

    public ConfirmOrderDialog(Order order) {
        this.order = order;
        this.stage = new Stage();
        this.orderService = new OrderService();

        Label lblCustomer = new Label("Customer:");

        Label lblCustomerDetails = new Label();
        lblCustomerDetails.setText(order.getCustomer().toString());

        GridPane gridPane = makeGridPaneLayout();

        Label lblTotalPrice = new Label();
        lblTotalPrice.setText(String.format("%s", order.getTotalPrice()));

        Button btnConfirm = new Button("Confirm");
        btnConfirm.setOnAction(
                a -> {
                    if (order.getCustomer() != null && order.getOrderItems().size() != 0) {
                        order.setPurchaseDate(LocalDate.now());
                        orderService.AddNewOrder(order);
                        confirmed = true;
                        stage.close();
                    }else
                        System.out.println("shity shity ");
                });
        btnConfirm.setPrefSize(125, 35);
        VBox vBox = new VBox();

        vBox.getChildren().addAll(lblCustomer, lblCustomerDetails, gridPane, lblTotalPrice, btnConfirm);
        this.stage.setScene( new Scene(vBox, 900, 700));

    }

    private void makeConfirmationScene() {

    }
    private GridPane makeGridPaneLayout() {

        Label lblQty = new Label("Qty");
        Label lblBrand = new Label("Brand");
        Label lblModel = new Label("Model");
        Label lblType = new Label("Type");
        Label lblPrice = new Label("Price");

        Label quantityLabel = new Label();
        Label brandLabel = new Label();
        Label modelLabel = new Label();
        Label typeLabel = new Label();
        Label priceLabel = new Label();

        for (OrderItem oi : order.getOrderItems()) {
            quantityLabel.setText(quantityLabel.getText() + oi.getQuantity() + "\n");
            brandLabel.setText(brandLabel.getText() + oi.getGuitar().getBrand() + "\n");
            modelLabel.setText(modelLabel.getText() + oi.getGuitar().getModel() + "\n");
            typeLabel.setText(typeLabel.getText() + oi.getGuitar().getType().toString() + "\n");
            priceLabel.setText(priceLabel.getText() + oi.getGuitar().getPrice() + "\n");
        }

        GridPane gridPane = new GridPane();

        GridPane.setConstraints(lblQty, 0, 0);
        GridPane.setConstraints(lblBrand, 1, 0);
        GridPane.setConstraints(lblModel, 2, 0);
        GridPane.setConstraints(lblType, 3, 0);
        GridPane.setConstraints(lblPrice, 4, 0);
        GridPane.setConstraints(quantityLabel, 0, 1);
        GridPane.setConstraints(brandLabel, 1, 1);
        GridPane.setConstraints(modelLabel, 2, 1);
        GridPane.setConstraints(typeLabel, 3, 1);
        GridPane.setConstraints(priceLabel, 4, 1);

        gridPane
                .getChildren()
                .addAll(
                        lblQty,
                        lblBrand,
                        lblModel,
                        lblType,
                        lblPrice,
                        quantityLabel,
                        brandLabel,
                        modelLabel,
                        typeLabel,
                        priceLabel);

        return gridPane;
    }
}
