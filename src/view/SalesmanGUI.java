package view;

import controller.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class SalesmanGUI {
    private JPanel panel1;
    private JButton logoutButton;
    private JButton reloadButton;
    private JTabbedPane tabbedPane1;
    private JTextField searchT;
    private JButton SearchButton;
    private JSpinner spinner1;
    private JButton insertB;
    private JButton detailOrderB;
    private JTable orderTable;
    private JButton b4;
    private JLabel p4;
    private JLabel s4;
    private JLabel l4;
    private JButton b1;
    private JLabel p1;
    private JLabel s1;
    private JLabel l1;
    private JButton b5;
    private JLabel p5;
    private JLabel s5;
    private JLabel l5;
    private JButton b2;
    private JLabel p2;
    private JLabel s2;
    private JLabel l2;
    private JButton b6;
    private JLabel p6;
    private JLabel s6;
    private JLabel l6;
    private JButton b3;
    private JLabel p3;
    private JLabel s3;
    private JLabel l3;
    private JButton b7;
    private JLabel p7;
    private JLabel s7;
    private JLabel l7;
    private JButton b8;
    private JLabel p8;
    private JLabel s8;
    private JLabel l8;
    private JLabel nameL;
    private JPanel pa1;
    private JPanel pa2;
    private JPanel pa3;
    private JPanel pa4;
    private JPanel pa5;
    private JPanel pa6;
    private JPanel pa7;
    private JPanel pa8;
    private JScrollPane taPanel;
    Salesman salesman;
    private JLabel[] goodsNames;
    private JLabel[] goodsPrices;
    private JLabel[] goodsSolds;
    private JButton[] goodsButtons;
    private JPanel[] goodsPanels;
    private Goods goods;
    private Orders orders;
    private BufferedImage imgDefault;
    private JFrame jFrame;
    List<Order> orderList;

    private Connection conn;
    public SalesmanGUI(Salesman salesman, Connection conn){
        this.salesman =salesman;
        this.conn = conn;
        goods = new Goods(conn);
        orders = new Orders(conn);
        try {
            imgDefault = ImageIO.read(new File("src/Res/test.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imgDefault = Utils.resize(imgDefault, 150, 150);

        initGoodsI4();
        setI4();
        List<Item> itemShow = goods.searchName("");
        updateGoods(itemShow);

        jFrame = new JFrame("App");
        jFrame.setContentPane(this.panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        initButton();
        updateOrderTable();



    }
    private void initGoodsI4(){
        goodsButtons = new JButton[]{b1, b2, b3, b4, b5, b6, b7, b8};
        goodsNames = new JLabel[]{l1, l2, l3, l4, l5, l6, l7, l8};
        goodsPrices = new JLabel[]{p1, p2, p3, p4, p5, p6, p7, p8};
        goodsSolds = new JLabel[]{s1, s2, s3, s4, s5, s6, s7, s8};
        goodsPanels = new JPanel[]{pa1, pa2, pa3, pa4, pa5, pa6, pa7, pa8};
    }
    private void initButton(){
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                LoginGUI loginGUI = new LoginGUI(conn);
                jFrame.setVisible(false);
            }
        });
        insertB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                InsertItemGUI insertItemGUI = new InsertItemGUI(conn);
            }
        });
        SearchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                updateGoods(goods.searchName(searchT.getText()));
            }
        });
        detailOrderB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(orderTable.getSelectedRow()>=0) {
                    OrderDetailGUI orderDetailGUI = new OrderDetailGUI(conn, orderList.get(orderTable.getSelectedRow()));
                }
//                System.out.println(orderList.get(orderTable.getSelectedRow()).getId());
            }
        });
        reloadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                reload();
            }
        });
    }
    private void reload(){
        updateOrderTable();
        updateGoods(goods.searchName(""));
    }
    private void updateGoods(List<Item> items){
        resetGoods();
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

            try {
                BufferedImage img = item.getImage();
                if (img != null) {

                    goodsButtons[cnt].setIcon(new ImageIcon(img));
                }
            goodsButtons[cnt].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    ItemDetailGUI itemDetailGUI = new ItemDetailGUI(conn, item);
                }
            });
            }finally {}
            cnt++;
        }
    }
    private void resetGoods(){
        for (int i=0;i<8;i++){
            goodsNames[i].setText("");
            goodsPrices[i].setText("");
            goodsSolds[i].setText("");
            goodsButtons[i].setIcon( new ImageIcon(imgDefault));
            if (goodsButtons[i].getMouseListeners().length >0){
                goodsButtons[i].removeMouseListener(goodsButtons[i].getMouseListeners()[0]);
            }
        }
    }
    private void setI4(){nameL.setText(salesman.getName());
    }
    private void updateOrderTable(){
        String[] labelT = new String[] {"Tên khách hàng", "Tổng tiền", "Ngày đặt", "Trạng thái"};
        orderList = orders.searchName("");
        Object[][] data = new Object[orderList.size()][labelT.length];
        int cnt = 0;
        for (Order j :orderList){
            data[cnt][0] = j.getCustomer_username(); // Sửa thành tên khách hàng chứ k phải tên tk
            data[cnt][1] = j.getTotalPrice();
            data[cnt][2] = j.getOrderedTime();
            data[cnt][3] = Utils.stateString(j.getState());
            cnt++;
        }
        orderTable.setModel(new DefaultTableModel(data, labelT));
    }
}
