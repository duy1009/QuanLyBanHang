package controller;

import java.util.Date;

public class Salesman {
    private String id;
    private String name;
    private String sex;
    private Date date_of_birth;
    private String address;
    private String phone;
    private Date account_creation_date;
    private boolean state = false;
    private Goods goods;

    Salesman(String userName, String password){
        // get information from database
        // if not found -> state = false; else state = true
    }


//    public Item itemSearch(){
//        Item item = new Item();
//        return item;
//    }

    public void confirmOrder(){

    }

    public void rejectOrder(){

    }

    public void statistics(){}
    public boolean loginState(){return state;}
}
