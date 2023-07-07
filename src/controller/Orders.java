package controller;

import view.Utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orders {
    private Connection conn;
    private Goods goods;
    public Orders(Connection conn){
        this.conn = conn;
        goods = new Goods(conn);
    }

    public List<Order> searchName(String uname_kh){
        Statement stmt= null;
        List<Order> orders = new ArrayList<Order>();
        try {
            stmt = conn.createStatement();
            String SQL;
            if (uname_kh==""){
                SQL = "SELECT * FROM `donhang`";
            }else{
                SQL = "SELECT * FROM `donhang` WHERE tk_kh="+ uname_kh;
            }

            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()){
                Order order = null;
                List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
//                String mdh = rs.getString("mdh");
                Statement stmt2 = conn.createStatement();
                ResultSet rs2 = stmt2.executeQuery("SELECT * FROM `chitietsp` WHERE mdh=\""+ rs.getString("mdh")+ "\"");
                while (rs2.next()){
//                        Item itemTemp= goods.getItemByID(rs2.getInt("msp"));
//                    System.out.println(rs2.getInt("mctsp"));
                    OrderDetail temp = new OrderDetail(
                            rs2.getInt("mctsp"),
                            rs2.getInt("msp"),
                            rs2.getInt("soluong"),
                            rs2.getLong("gia")
                            );
                    orderDetails.add(temp);
                }
                System.out.println(rs.getString("tk_nv"));
                Order order_t = new Order(
                        rs.getString("mdh"),
                        rs.getString("tk_nv"),
                        rs.getString("tk_kh"),
                        orderDetails,
                        rs.getDate("thoigian"),
                        rs.getInt("trangthai")
                );
                orders.add(order_t);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }
    public static Order searchByID(Connection conn, String id){
        Statement stmt= null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `donhang` WHERE mdh="+ id);
            if (rs.next()){
                List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
//                String mdh = rs.getString("mdh");
                Statement stmt2 = conn.createStatement();
                ResultSet rs2 = stmt2.executeQuery("SELECT * FROM `chitietsp` WHERE mdh=\""+ rs.getString("mdh")+ "\"");
                while (rs2.next()){
//                        Item itemTemp= goods.getItemByID(rs2.getInt("msp"));
//                    System.out.println(rs2.getInt("mctsp"));
                    OrderDetail temp = new OrderDetail(
                            rs2.getInt("mctsp"),
                            rs2.getInt("msp"),
                            rs2.getInt("soluong"),
                            rs2.getLong("gia")
                    );
                    orderDetails.add(temp);
                }

                Order order_t = new Order(
                        rs.getString("mdh"),
                        rs.getString("tk_nv"),
                        rs.getString("tk_kh"),
                        orderDetails,
                        rs.getDate("thoigian"),
                        rs.getInt("trangthai")
                );
                return order_t;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Item> searchDate(Date datePos){
        List<Item> item = null;
        return item;
    }


    public boolean addItem(
            String name,
            String describe,
            long price,
            int quantity_in_stock,
            int quantity_sold,
            String date_of_manufacture,
            String expiration_date,
            BufferedImage image
    ){

        try {
            String img = Utils.toByteArray(image, "png");
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO `sanpham`(`ten`, `mota`, `soluongtrongkho`, `soluongdaban`, `gia`, `nsx`, `hsd`, `ngaytao`, `anh`) VALUES ('" +
                    name +
                    "','" +
                    describe +
                    "'," +
                    quantity_in_stock +
                    "," +
                    quantity_sold +
                    "," +
                    price +
                    ",'" +
                    date_of_manufacture +
                    "','" +
                    expiration_date +
                    "','" +
                    date_of_manufacture +
                    "','" +
                    img +
                    "')");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Cập nhật lên database

        return true;
    }
    public boolean confOrder(String id){
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.executeUpdate(String.format("UPDATE `donhang` SET trangthai=2 WHERE mdh=(%s)", id) );
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        return true;
    }
    public boolean rejectOrder(String id){
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.executeUpdate(String.format("UPDATE `donhang` SET trangthai=3 WHERE mdh=(%s)", id) );
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
