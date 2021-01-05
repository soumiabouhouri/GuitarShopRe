package sample.DAO;

import sample.Model.Order;

import java.util.List;

public class OrderDAO {
    DataBase db = new DataBase();

    public List<Order> getAllOrders() {
        return db.getOrders();
    }

    public int getLastOrderId() {
        return db.getOrders().get((db.getOrders().size() - 1)).getOrderID();
    }

    public void AddNewOrder(Order order) {
        db.getOrders().add(order);

    }
}
