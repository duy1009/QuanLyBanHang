package controller;

public class OrderDetail {
    private Item item;
    private int quantity;
    private long price;
    OrderDetail (Item item, int quantity, int price){
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }
    long getTotalPrice(){
        return price*quantity;
    }
}
