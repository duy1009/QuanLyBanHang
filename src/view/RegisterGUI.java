package view;

import controller.Register;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class RegisterGUI {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton reB;
    private JButton backButton;
    private JComboBox comboBox1;
    private JComboBox ns2;
    private JComboBox ns3;
    private JTextField textField4;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JComboBox ns1;
    Connection conn;
    public RegisterGUI(Connection conn){
        this.conn = conn;
        JFrame jFrame = new JFrame("Register");
        jFrame.setContentPane(this.panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                LoginGUI loginGUI = new LoginGUI(conn);
                jFrame.setVisible(false);
            }
        });
        reB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // Check cú pháp
                // Check mật khẩu

                String ns = getDate(ns1, ns2, ns3);
                if (!Register.register(
                        textField3.getText(),
                        comboBox1.getSelectedIndex(),
                        textField1.getText(),
                        textField2.getText(),
                        ns,
                        textField4.getText(),
                        new String (passwordField1.getPassword())
                )){
                    // Thong bao nhap sai
                }
            }
        });
    }
    private String getDate(JComboBox day, JComboBox month, JComboBox year){
        return year.getSelectedItem() +"-"+ month.getSelectedItem() +"-"+ day.getSelectedItem();
    }

}
