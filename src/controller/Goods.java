package controller;

import java.util.Date;
import java.util.List;

public class Goods {
    private List<Item> items;
    Goods(){
        // Get information form database
    }

    public Item searchName(String name){
        Item item = new Item();
        return item;
    }

    public Item searchDate(Date datePos){
        Item item = new Item();
        return item;
    }

    public void updateItem(Item item){
        // Search id
        // update information and date
        // update on database
    }
    public boolean addItem(){
        return true;
    }
    public boolean deleteItem(){
        return true;
    }
    public boolean editItem(){
        return true;    }
}
