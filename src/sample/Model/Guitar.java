package sample.Model;


public class Guitar {
    private int GuitarID;
    private int quantity;
    private String brand;
    private String model;
    private boolean acoustic;
    private Type type;
    private double price;

    public Guitar(int guitarID, int quantity, String brand, String model, boolean acoustic, Type type, double price) {
        GuitarID = guitarID;
        this.quantity = quantity;
        this.brand = brand;
        this.model = model;
        this.acoustic = acoustic;
        this.type = type;
        this.price = price;
    }

    public int getGuitarID() {
        return GuitarID;
    }

    public void setGuitarID(int guitarID) {
        GuitarID = guitarID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAcoustic() {
        return acoustic;
    }

    public void setAcoustic(boolean acoustic) {
        this.acoustic = acoustic;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Guitar{" +
                "GuitarID=" + GuitarID +
                ", quantity=" + quantity +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", acoustic=" + acoustic +
                ", type=" + type +
                ", price=" + price +
                '}';
    }
}
