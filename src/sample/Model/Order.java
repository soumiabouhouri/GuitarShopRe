package sample.Model;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private int orderID;
    private List<OrderItem> orderItems;
    private Customer customer;
    private double totalPrice;
    private LocalDate purchaseDate;

    public Order(int orderID, List<OrderItem> orderItems, Customer customer, LocalDate purchaseDate) {
        this.orderID = orderID;
        this.orderItems = orderItems;
        this.customer = customer;
        this.purchaseDate = purchaseDate;
    }

    public Order() {

    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public double getTotalPrice() {
        double total = 0;
        for (OrderItem item : orderItems) {
            total += (item.getGuitar().getPrice() * item.getQuantity());
        }

        return total;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", orderItems=" + orderItems +
                ", customer=" + customer +
                ", totalPrice=" + totalPrice +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
