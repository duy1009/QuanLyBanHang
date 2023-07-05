package view;

import controller.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CustomerGUI {
    private JPanel panel1;
    private JButton đăngXuấtButton;
    private JSpinner spinner1;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JButton tìmKiếmButton;
    private JTable orderTable;
    private JButton cậpNhậtButton;
    private JTable table1;
    private JButton xóaButton;
    private JButton đặtHàngButton;
    private JButton hủyButton;
    private JLabel nameL;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel l5;
    private JLabel l6;
    private JLabel l7;
    private JLabel l8;
    private JLabel p1;
    private JLabel p2;
    private JLabel p3;
    private JLabel p4;
    private JLabel p5;
    private JLabel p6;
    private JLabel p7;
    private JLabel p8;
    private JLabel s1;
    private JLabel s2;
    private JLabel s3;
    private JLabel s4;
    private JLabel s5;
    private JLabel s6;
    private JLabel s7;
    private JLabel s8;

    private Customer customer;

    public CustomerGUI(Customer customer) {
        this.customer = customer;
        JFrame jFrame = new JFrame("app");
        jFrame.setContentPane(this.panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        createOrderTable();
    }

    private void createOrderTable(){
        Object[][] data = {
                {"a", 1, 2, 3},
                {"adas", 2, 3 ,5},
                {"df", 1, 2, 3},
                {"rtasas", 2, 3 ,5}
        };
        orderTable.setModel(new DefaultTableModel(
                data,
                new String[] {"Name", "num1", "num2", "num3"}
        ));
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


}
