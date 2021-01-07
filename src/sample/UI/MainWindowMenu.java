package sample.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.DAO.DataBase;
import sample.Model.Order;
import sample.Model.User;
import sample.UI.scenes.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class MainWindowMenu {
    MenuBar menuBar;
    Menu menuHome, menuSales, menuStock;
    MenuItem exit, Orders, OrdersList, StockMaintenance, about;
    MenuItem ListOfArticles;
    DataBase db;
    private  Stage stage;
    private User currentUser;
    public MainWindowMenu() {
        db = new DataBase();
        stage = new Stage();
        HBox layout = new HBox();
        layout.setPadding(new Insets(0, 20, 20, 20));
        layout.setSpacing(20);
        stage.setTitle("Guitar ShopFx Dashboard");
        stage.setFullScreen(false);
        menuBar = new MenuBar();

        menuHome = new Menu("Home");
        exit = new MenuItem("Exit");
        about = new MenuItem("about");
        menuHome.getItems().addAll(exit, about);

        menuSales = new Menu("Sales");
        Orders = new MenuItem("Orders");
        OrdersList = new MenuItem("Orders List");
        menuSales.getItems().addAll(Orders, OrdersList);

        menuStock = new Menu("Stock");
        ListOfArticles = new MenuItem("List of our Articles");
        StockMaintenance = new MenuItem("Maintain");
        menuStock.getItems().addAll(ListOfArticles, StockMaintenance);

        menuBar.getMenus().addAll(menuHome, menuSales, menuStock);
        layout.getChildren().addAll(menuBar);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
        ListOfArticles.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                GuitarListScene stm = new GuitarListScene();
                layout.getChildren().remove(1);
                layout.getChildren().add(stm.getScene().getRoot());
            }
        });
        StockMaintenance.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                StockScene stockScene = new StockScene();
                layout.getChildren().remove(1);
                layout.getChildren().add(stockScene.getScene().getRoot());
            }
        });
        Orders.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                OrdersScene ordersScene = new OrdersScene();
                layout.getChildren().remove(1);
                layout.getChildren().add(ordersScene.getScene().getRoot());
            }
        });

        WelcomeScene sl = new WelcomeScene();
        layout.getChildren().addAll(sl.getScene().getRoot());
        stage.setScene(new Scene(layout, 990, 770));
        stage.show();
        // let us go
    }
    public Stage getStage() {
        return stage;
    }


}
