package sample.DAO;

import sample.Model.Customer;

import java.util.List;

public class CustomerDao {
    DataBase database = new DataBase();

    public List<Customer> getAllCustomers() {
        return database.getCustomers();
    }
}
