package controller;

import view.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Goods {
//    private List<Item> items;
    Connection conn;

    public Goods(Connection conn){
        this.conn = conn;
    }

    public List<Item> searchName(String name){
        Statement stmt= null;
        List<Item> items = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `sanpham` WHERE ten LIKE \"%"+ name+ "%\"");
            while (rs.next()){
                Item item = null;
                try {
                    item = new Item(
                            rs.getString("msp"),
                            rs.getString("ten"),
                            rs.getString("mota"),
                            rs.getLong("gia"),
                            rs.getInt("soluongtrongkho"),
                            rs.getInt("soluongdaban"),
                            rs.getDate("nsx"),
                            rs.getDate("hsd"),
                            rs.getDate("ngaytao"),
                            Utils.toBufferedImage(Utils.Blob2String(rs.getBlob("anh")))
                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }
    public static Item getItemByID(Connection conn, int id){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `sanpham` WHERE msp=\""+ id + "\"");
            if(rs.next()){
                try {
                    return new Item(
                            rs.getString("msp"),
                            rs.getString("ten"),
                            rs.getString("mota"),
                            rs.getLong("gia"),
                            rs.getInt("soluongtrongkho"),
                            rs.getInt("soluongdaban"),
                            rs.getDate("nsx"),
                            rs.getDate("hsd"),
                            rs.getDate("ngaytao"),
                            Utils.toBufferedImage(Utils.Blob2String(rs.getBlob("anh")))
                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else return null;
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
    public boolean deleteItem(String msp){
        // Kiểm tra tồn tại
        // Xóa trên database
        return true;
    }
    public boolean editItem(Item item){
        // Kiểm tra tồn tại
        // Sửa các giá trị trừ msp
        return true;    }
}
