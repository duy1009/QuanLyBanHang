package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Goods {
    private List<Item> items;
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
                Item item = new Item(
                        rs.getString("msp"),
                        rs.getString("ten"),
                        rs.getString("mota"),
                        rs.getLong("gia"),
                        rs.getInt("soluongtrongkho"),
                        rs.getInt("soluongdaban"),
                        rs.getDate("nsx"),
                        rs.getDate("hsd"),
                        rs.getDate("ngaytao")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }

    public List<Item> searchDate(Date datePos){
        List<Item> item = null;
        return item;
    }

    public void updateItem(Item item){
        // Search id
        // update information and date
        // update on database
    }
    public boolean addItem(Item item){
        // kiểm tra tồn tại
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
