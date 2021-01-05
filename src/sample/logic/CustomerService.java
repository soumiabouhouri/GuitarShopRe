package sample.logic;

import sample.DAO.CustomerDao;
import sample.Model.Customer;

import java.util.List;

public class CustomerService {
    CustomerDao customerDAO = new CustomerDao();

    public List<Customer> getAllCustomers() {

        return customerDAO.getAllCustomers();
    }
}
