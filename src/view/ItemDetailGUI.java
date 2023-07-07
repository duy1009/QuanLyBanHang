package view;

import controller.Item;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class ItemDetailGUI {
    private JPanel panel1;
    private JButton editB;
    private JButton xButton;
    private JButton deleteButton;
    private JLabel name;
    private JLabel de;
    private JLabel state;
    private JLabel sold;
    private JLabel priceAll;
    private JLabel nsx;
    private JLabel hsd;
    private JLabel image;
    private JLabel stock;
    private JLabel price;
    Connection conn;
    Item item;
    public ItemDetailGUI(Connection conn, Item item) {
        this.conn = conn;
        this.item = item;

        JFrame jFrame = new JFrame("Detail");
        jFrame.setContentPane(this.panel1);
        jFrame.setUndecorated(true);

        setI4();
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        xButton.setBorder(BorderFactory.createEmptyBorder());
        xButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jFrame.setVisible(false);
            }
        });
        editB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
    }
    private void setI4(){
        name.setText(item.getName());
        de.setText(item.getDescribe());
        stock.setText(Integer.toString(item.getQuantity_in_stock()));
        sold.setText(Integer.toString(item.getQuantity_sold()));
        price.setText(Long.toString(item.getPrice()));
        nsx.setText(item.getDate_of_manufacture().toString());
        hsd.setText(item.getExpiration_date().toString());
        image.setIcon(new ImageIcon(item.getImage()));
    }
}
