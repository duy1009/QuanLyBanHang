package controller;

import java.util.Date;

public class Item {
    private String id;
    private String name;
    private String describe;
    private long price;
    private int quantity_in_stock;
    private int quantity_sold;
    private Date date_of_manufacture;
    private Date expiration_date;
    private Date posting_date;

    public Item (){}
    public Item(String id, String name, String describe, long price, int quantity_in_stock, int quantity_sold, Date date_of_manufacture, Date expiration_date) {
        this.name = name;
        this.describe = describe;
        this.price = price;
        this.quantity_in_stock = quantity_in_stock;
        this.quantity_sold = quantity_sold;
        this.date_of_manufacture = date_of_manufacture;
        this.expiration_date = expiration_date;
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    public long getPrice() {
        return price;
    }

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public int getQuantity_sold() {
        return quantity_sold;
    }

    public Date getDate_of_manufacture() {
        return date_of_manufacture;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public Date getPosting_date() {
        return posting_date;
    }

    public String getId() {
        return id;
    }
}
