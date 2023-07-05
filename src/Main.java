import controller.Salesman;
import view.CustomerGUI;
import view.LoginGUI;
import view.RegisterGUI;
import view.SalesmanGUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();
//        try(Connection conn = connectJDBC.connect();){
//            Statement stmt=conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM nhanvien");
//            while (rs.next()) {
//                System.out.print("ID: " + rs.getString("ten"));
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        LoginGUI loginGUI = new LoginGUI(conn);
//        Menu a = new Menu();
        ;


//        CustomerGUI a = new CustomerGUI();
    }
}