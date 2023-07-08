package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class Login {
    Connection conn;
    public Login(Connection conn){
        this.conn = conn;
    }
    public Customer loginCustomer(String userName, String password){
        Statement stmt= null;
        Customer customer = new Customer();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `khachhang` WHERE tk_kh=\"" + userName +"\" AND mk=\"" + password +"\"");
            if (rs.next()){
                customer.setInformation(
                        userName,
                        rs.getString("ten"),
                        rs.getString("gioitinh"),
                        rs.getDate("ns"),
                        rs.getString("diachi"),
                        rs.getString("sdt"),
                        rs.getDate("ngaytaotk"),
                        true
                        );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

         return customer;
    }

    public Salesman loginSalesman(String userName, String password){
        Statement stmt= null;
        Salesman salesman = new Salesman();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `nhanvien` WHERE tk_nv=\"" + userName +"\" AND mk=\"" + password +"\"");
            if (rs.next()){
                salesman.setInformation(
                        userName,
                        rs.getString("ten"),
                        rs.getString("gioitinh"),
                        rs.getDate("ns"),
                        rs.getString("diachi"),
                        rs.getString("sdt"),
                        rs.getDate("ngaytaotk"),
                        true
                );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return salesman;
    }

}
