package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Register {
    private Connection conn;
    public Register(Connection conn){this.conn = conn;}

    public boolean register(String name,
                            int sex,
                            String addr,
                            String phone,
                            String dob,
                            String username,
                            String password){
        boolean success = false;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM `khachhang` WHERE tk_kh=\"%s\"", username));
            if (!rs.next()){
                Statement st2 = conn.createStatement();
                st2.executeUpdate(String.format("INSERT INTO `khachhang`(`tk_kh`, `mk`, `ten`, `gioitinh`, `ns`, `diachi`, `sdt`, `ngaytaotk`) " +
                        "VALUES ('%s', '%s', '%s', '%d', '%s', '%s', '%s', '%s')", username, password, name, sex, dob, addr, phone, "2023-7-7"));
                success = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return success;
    }
}
