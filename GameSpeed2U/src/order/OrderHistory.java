package order;

import java.util.ArrayList;

public class OrderHistory {
    private String id;
    private String date;
    private String user;
    private String status;
    private double totalPrice;
    private ArrayList<OrderItem> items;

    public OrderHistory(String id, String date, String user, String status, double totalPrice, ArrayList<OrderItem> items) {
        setId(id);
        setDate(date);
        setUser(user);
        setStatus(status);
        setTotalPrice(totalPrice);
        setItems(items);
    }

    public OrderHistory() {};

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }
}
