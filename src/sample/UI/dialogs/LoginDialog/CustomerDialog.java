package sample.UI.dialogs.LoginDialog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Model.Customer;
import sample.logic.CustomerService;

public class CustomerDialog {
    private Stage stage;
    public Customer getCustomer() {
        return customer;
    }
    private Customer customer;
    CustomerService customerService;
    ObservableList<Customer> customers;
    TableView<Customer> customerTableView;

    public Stage getStage() {
        return stage;
    }

    public CustomerDialog() {

        customerTableView = new TableView<>();
        this.stage = new Stage();
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.setResizable(false);
        this.customerService = new CustomerService();
        stage.setTitle("Guitar shop FX - Search Customer");

        makeCustomerWindowScene();
    }

    private void makeCustomerWindowScene() {

        makeTableView();
        Button btnAddCustomer = new Button("Add Customer");
        btnAddCustomer.setOnAction(actionEvent ->
        {
           /* AddCustomerWindow addCustomerWindow = new AddCustomerWindow();
            addCustomerWindow.getStage().showAndWait();
            if(addCustomerWindow.getCustomer() != null)
            {
                customers.add(addCustomerWindow.getCustomer());
            }*/

        });
        VBox vBox = new VBox();
        vBox.getChildren().addAll(customerTableView, btnAddCustomer);

        Scene customerWindowScene = new Scene(vBox, 900, 700);
        this.stage.setScene(customerWindowScene);
    }

    private void makeTableView() {
        TableColumn<Customer, String> colFirstName = new TableColumn<>("First Name");
        colFirstName.setMinWidth(150);
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Customer, String> colLastName = new TableColumn<>("Last Name");
        colLastName.setMinWidth(150);
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Customer, String> colStreetAddress = new TableColumn<>("Street Address");
        colStreetAddress.setMinWidth(150);
        colStreetAddress.setCellValueFactory(new PropertyValueFactory<>("streetAddress"));

        TableColumn<Customer, String> colCity = new TableColumn<>("City");
        colCity.setMinWidth(150);
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Customer, String> colPhone = new TableColumn<>("Phone #");
        colPhone.setMinWidth(150);
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));

        TableColumn<Customer, String> colEmail = new TableColumn<>("Email Address");
        colEmail.setMinWidth(160);
        colEmail.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));

        this.customerTableView.getColumns().addAll(colFirstName, colLastName, colStreetAddress, colCity, colPhone, colEmail);
         this.customerTableView.setItems(FXCollections.observableArrayList(customerService.getAllCustomers()));
         // with double click we can select the customer we want
        SelectCustomerWithDoubleClick();
    }
    private void SelectCustomerWithDoubleClick() {
        this.customerTableView.setRowFactory(
                customerTableView -> {
                    TableRow<Customer> row = new TableRow<>();
                    row.setOnMouseClicked(
                            mouseEvent -> {
                                if (mouseEvent.getClickCount() == 2 && !(row.isEmpty())) {
                                    customer = row.getItem();
                                    System.out.println(customer.getFirstName());
                                    stage.close();
                                }
                            });

                    return row;
                });

        customers = FXCollections.observableArrayList(customerService.getAllCustomers());
    }


    /// useless code
    private void makeTableViewWork() {

       /* this.customerTableView.setRowFactory(
                customerTableView -> {
                    TableRow<Customer> row = new TableRow<>();
                    row.setOnMouseClicked(
                            mouseEvent -> {
                                if (mouseEvent.getClickCount() == 2 && !(row.isEmpty())) {
                                    customer = row.getItem();
                                    System.out.println(customer.getFirstName());
                                    stage.close();
                                }
                            });

                    return row;
                });

        customers = FXCollections.observableArrayList(customerService.getAllCustomers());
        FilteredList<Customer> filteredCustomers = new FilteredList<>(customers, a -> true);

        txtSearch
                .textProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                                filteredCustomers.setPredicate(
                                        customer -> {
                                            // if filter text is empty display all of the data
                                            if (newValue == null || newValue.isEmpty()) {
                                                return true; // if the search text is empty all the list will be shown
                                            }
                                            String filterText = newValue.toLowerCase();

                                            if (customer.getLastName().toLowerCase().contains(filterText)) {
                                                return true; // if the search text matches the last name
                                            } else if (customer.getFirstName().toLowerCase().contains(filterText)) {
                                                return true; // if the search text matches the first name
                                            } else if (customer.getCity().toLowerCase().contains(filterText)) {
                                                return true; // if the search text matches the city
                                            } else {
                                                return false; // search text does not match any of the above, the list will
                                                // be empty
                                            }
                                        }));
        //wrap it in a sorted list
        SortedList<Customer> sortedList = new SortedList<>(filteredCustomers);
        this.customerTableView.setItems(sortedList);*/
    }
}
