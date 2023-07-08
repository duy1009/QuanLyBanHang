package controller;

import java.util.Date;

public class Salesman {
    private String uname;

    private String name;
    private String sex;
    private Date date_of_birth;
    private String address;
    private String phone;

    public String getUname() {
        return uname;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Date getAccount_creation_date() {
        return account_creation_date;
    }

    public boolean isState() {
        return state;
    }

    public Goods getGoods() {
        return goods;
    }

    private Date account_creation_date;
    private boolean state = false;
    private Goods goods;

    public Salesman(){
        // get information form database
        // if not found -> state = false; else state = true
    }

    public Salesman(String name, String sex, Date date_of_birth, String address, String phone, Date account_creation_date) {
        this.name = name;
        this.sex = sex;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone = phone;
        this.account_creation_date = account_creation_date;
    }
    public void setInformation(String uname, String name, String sex, Date date_of_birth, String address, String phone, Date account_creation_date, boolean state) {
        this.uname = uname;
        this.name = name;
        this.sex = sex;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone = phone;
        this.account_creation_date = account_creation_date;
        this.state = state;
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
