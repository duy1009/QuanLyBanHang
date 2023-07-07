package view;

import controller.Item;
import controller.Order;
import controller.OrderDetail;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class ItemDetailGUI_2 {
    private JPanel panel1;
    private JButton xButton;
    private JLabel name;
    private JLabel stock;
    private JLabel sold;
    private JLabel price;
    private JLabel nsx;
    private JLabel hsd;
    private JLabel image;
    private JButton addCard;
    private JLabel de;
    private JTextField quanlity;
    private JLabel state;
    private Connection conn;
    private Order card;
    private Item item;
    public ItemDetailGUI_2(Connection conn, Item item, Order card) {
        this.conn = conn;
        this.item = item;
        this.card = card;

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

        addCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int id = Integer.parseInt(item.getId());
                if (card.getOrderDetails()!=null){
                    for(OrderDetail orderDetail:card.getOrderDetails()) {
                        if (id == orderDetail.getIdItem()) {
                            state.setText("Sản phẩm đã trong giỏ hàng");
                            return;
                        }
                    }
                }

                OrderDetail odtemp = new OrderDetail(0, id, Integer.parseInt(quanlity.getText()), item.getPrice());
                card.getOrderDetails().add(odtemp);
                state.setText("Thêm thành công!");


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
