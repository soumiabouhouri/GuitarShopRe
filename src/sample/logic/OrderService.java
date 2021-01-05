package sample.logic;

import sample.DAO.OrderDAO;
import sample.DAO.guitarDao;
import sample.Model.Order;
import sample.Model.OrderItem;

import java.util.List;

public class OrderService {
    OrderDAO orderDAO = new OrderDAO();
    guitarDao articleDAO = new guitarDao();

    public List<Order> getAllOrders()
    {
        return orderDAO.getAllOrders();
    }
    public int getOrderId()
    {
        return orderDAO.getLastOrderId() + 21;
    }
    public void AddNewOrder(Order order)
    {
        orderDAO.AddNewOrder(order);
        for (OrderItem oi  : order.getOrderItems()) {
            articleDAO.reduceStockAmount(oi.getQuantity(), oi.getGuitar(), true);
        }
    }
}
