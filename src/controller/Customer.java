package controller;
import view.Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public static Customer getCustomerByUN(Connection conn, String uname){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            System.out.println(uname);
            ResultSet rs = stmt.executeQuery("SELECT * FROM `khachhang` WHERE tk_kh=\""+ uname +"\"");

            if(rs.next()){
                System.out.println(uname);
                return new Customer(
                                rs.getString("ten"),
                                rs.getString("gioitinh"),
                                rs.getDate("ns"),
                                rs.getString("diachi"),
                                rs.getString("sdt"),
                                rs.getDate("ngaytaotk")
                            );
            }else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean loginState(){
        return state;
    }

}
