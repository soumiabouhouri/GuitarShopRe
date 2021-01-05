package sample.Model;

public class OrderItem {
    private Guitar guitar;
    private int quantity;

    public OrderItem(Guitar guitar, int quantity) {
        this.guitar = guitar;
        this.quantity = quantity;
    }

    public Guitar getGuitar() {
        return guitar;
    }

    public void setGuitar(Guitar guitar) {
        this.guitar = guitar;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "guitar=" + guitar +
                ", quantity=" + quantity +
                '}';
    }
}
