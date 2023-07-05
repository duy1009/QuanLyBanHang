package view;

import controller.Customer;
import controller.Goods;
import controller.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.List;

public class CustomerGUI {
    private JPanel panel1;
    private JButton logoutButton;
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
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;

    private JLabel[] goodsNames;
    private JLabel[] goodsPrices;
    private JLabel[] goodsSolds;
    private JButton[] goodsButtons;
    private Customer customer;
    Connection conn;
    private int page = 0;
    private Goods goods;

    public CustomerGUI(Customer customer, Connection conn) {
        this.customer = customer;
        this.conn = conn;

        goods = new Goods(conn);
        JFrame jFrame = new JFrame("App");
        initGoodsI4();
        setI4();

        List<Item> itemShow = goods.searchName("");
        updateGoods(itemShow);

        jFrame.setContentPane(this.panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        createOrderTable();
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                LoginGUI loginGUI = new LoginGUI(conn);
                jFrame.setVisible(false);
            }
        });
    }
    private void initGoodsI4(){
        goodsButtons = new JButton[]{b1, b2, b3, b4, b5, b6, b7, b8};
        goodsNames = new JLabel[]{l1, l2, l3, l4, l5, l6, l7, l8};
        goodsPrices = new JLabel[]{p1, p2, p3, p4, p5, p6, p7, p8};
        goodsSolds = new JLabel[]{s1, s2, s3, s4, s5, s6, s7, s8};
    }
    private void updateGoods(List<Item> items){
        int cnt = 0;
        for (Item item:items){
            if (cnt >=8){
                break;
            }
            goodsNames[cnt].setText(item.getName());
            goodsPrices[cnt].setText(Long.toString(item.getPrice()) + "đ");
            if (item.getQuantity_sold() > 1000){
                int i = item.getQuantity_sold() / 1000;
                goodsSolds[cnt].setText("Đã bán: "+Integer.toString(i) +"k");
            }else{
                goodsSolds[cnt].setText("Đã bán: "+Integer.toString(item.getQuantity_sold()));
            }

            cnt++;
        }

    }
    private void setI4(){
        nameL.setText(customer.getName());
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
