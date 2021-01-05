package sample.DAO;

import sample.Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private List<User> users;
    private List<Customer> customers;
    private List<Guitar> guitars;
    private List<Order> orders;

    public List<User> getUsers() {
        return users;
    }
    public List<Customer> getCustomers() {
        return customers;
    }
    public List<Guitar> getGuitars() {
        return guitars;
    }
    public List<Order> getOrders() {
        return orders;
    }

    public DataBase() {
        this.users = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.guitars = new ArrayList<>();
        this.orders = new ArrayList<>();
        setUsers();
        setGuitars();
        setCustomers();
        setOrders();
    }

    private void setUsers() {
        User manager = new User("soumia","bouhouri","123",UserRole.MANAGER);
        User salesMan = new User("adam","padam","000",UserRole.SALES);
        users.add(manager);
        users.add(salesMan);
    }

    private void setCustomers() {

        Customer customer1 = new Customer(21, "soumia", "bouhouri", "11 22", "0222222", "haarlem", "x@gmail.com");
        Customer customer2 = new Customer(42, "klaus", "ullrich", "1122", "01111111", "Den Haag", "y@gmail.com");
        Customer customer3 = new Customer(63, "adam", "ullrich", "899", "02121212", "rijswijk", "z@gmail.com");
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
    }

    private void setGuitars() {

        Guitar article1 = new Guitar(1, 22, "Gibson", "Model 1 ", false, Type.BASS, 150.75);
        Guitar article2 = new Guitar(2, 10, "Guild", "Mode 2", true, Type.ELECTRONIC, 450.75);
        guitars.add(article1);
        guitars.add(article2);
    }

    private void setOrders() {

        OrderItem oitem1 = new OrderItem(guitars.get(0), 3);
        OrderItem oitem2 = new OrderItem(guitars.get(1), 2);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(oitem1);
        orderItems.add(oitem2);

        List<OrderItem> orderItems1 = new ArrayList<>();
        orderItems1.add(oitem2);

        Order order1 = new Order(21, orderItems, customers.get(0), LocalDate.parse("2020-10-26"));
        Order order2 = new Order(42, orderItems1, customers.get(1), LocalDate.parse("2020-12-26"));
        orders.add(order1);
        orders.add(order2);
    }
}
