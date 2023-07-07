package controller;

public class OrderDetail {
//    private Item item;
    int id;
    private int idItem;
    private int quantity;

    public int getId() {
        return id;
    }

    public int getIdItem() {
        return idItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getPrice() {
        return price;
    }

    private long price;
    OrderDetail (int ID, int idItem, int quantity, long price){
        this.id = ID;
        this.idItem = idItem;
        this.quantity = quantity;
        this.price = price;
    }
    long getTotalPrice(){
        return price*quantity;
    }
}
