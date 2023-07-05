package view;

import controller.Customer;
import controller.Login;
import controller.Salesman;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static controller.Login.loginCustomer;

public class LoginGUI {
    private JPanel panel1;
    private JButton registerB;
    private JButton loginB;
    private JTextField textField4;
    private JPasswordField passwordField1;
    private JComboBox comboBox1;
    Connection conn;

    public LoginGUI(Connection conn){
        this.conn = conn;
        JFrame jFrame = new JFrame("Login");
        jFrame.setContentPane(this.panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        registerB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                RegisterGUI registerGUI = new RegisterGUI(conn);
                jFrame.setVisible(false);
            }
        });
        loginB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (comboBox1.getSelectedIndex() == 0){
                    Customer cus = Login.loginCustomer(textField4.getText(), new String(passwordField1.getPassword()));
                    if (cus.loginState()){
                        CustomerGUI customerGUI = new CustomerGUI(cus);
                        jFrame.setVisible(false);
                    }


                }
                else if (comboBox1.getSelectedIndex() == 1){
                    Salesman sal = Login.loginSalesman(textField4.getText(), new String(passwordField1.getPassword()));
                    if (sal.loginState()){
                        SalesmanGUI salesmanGUI = new SalesmanGUI(sal);
                        jFrame.setVisible(false);
                    }
                }
//                Statement stmt= null;
//                try {
//                    stmt = conn.createStatement();
//                    ResultSet rs = stmt.executeQuery("SELECT * FROM nhanvien");
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }

            }
        });
    }

}
