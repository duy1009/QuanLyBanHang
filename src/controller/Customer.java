package controller;
import java.util.Date;

public class Customer {
    private String name;
    private String sex;
    private Date date_of_birth;
    private String address;
    private String phone;
    private Date account_creation_date;
    private boolean state = false;

    public Customer(){
        // get information form database
        // if not found -> state = false; else state = true
    }

    Customer(String name, String sex, Date date_of_birth, String address, String phone, Date account_creation_date) {
        this.name = name;
        this.sex = sex;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone = phone;
        this.account_creation_date = account_creation_date;
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

    public void setInformation(String name, String sex, Date date_of_birth, String address, String phone, Date account_creation_date, boolean state) {
        this.name = name;
        this.sex = sex;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone = phone;
        this.account_creation_date = account_creation_date;
        this.state = state;
    }
    public boolean loginState(){
        return state;
    }

}
