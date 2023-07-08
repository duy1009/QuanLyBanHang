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
import java.util.ArrayList;
import java.util.List;

public class CustomerGUI {
    private JPanel panel1;
    private JButton logoutButton;
    private JSpinner spinner1;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JButton tìmKiếmButton;
    private JTable orderTable;
    private JButton reloadButton;
    private JTable cardTable;
    private JButton xóaButton;
    private JButton dhButton;
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
    private JButton detailOrderB;
    private JLabel priceAll;
    private JLabel stateOd;

    private JLabel[] goodsNames;
    private JLabel[] goodsPrices;
    private JLabel[] goodsSolds;
    private JButton[] goodsButtons;
    private Customer customer;
    Connection conn;
    private int page = 0;
    private BufferedImage imgDefault;
    private Goods goods;
    private Order card;
    List<Order> orderList;
    private Orders orders;
    private JFrame jFrame;

    public CustomerGUI(Customer customer, Connection conn) {
        this.customer = customer;
        this.conn = conn;
        orders = new Orders(conn);


        goods = new Goods(conn);
        jFrame = new JFrame("App");
        initGoodsI4();
        setI4();

        List<Item> itemShow = goods.searchName("");
        updateGoods(itemShow);

        jFrame.setContentPane(this.panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        updateOrderTable();
        updateCardTable();
        initButton();
        reloadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                updateOrderTable();
                updateGoods(goods.searchName(""));
                updateCardTable();
            }
        });
        dhButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                System.out.println(card.getOrderDetails().size());
                if (orders.createOrder(card)){
                    stateOd.setText("Đặt hàng thành công!");
                }
                else {
                    stateOd.setText("Thất bại");
                }
            }
        });
        hủyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                resetCard();
            }
        });
    }
    private void resetCard(){
        card = new Order("0", "", customer.getUname(), new ArrayList<>(), null, 0);
        stateOd.setText("");
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
        detailOrderB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(orderTable.getSelectedRow()>=0) {
                    OrderDetailGUI_2 orderDetailGUI_2 = new OrderDetailGUI_2(conn, orderList.get(orderTable.getSelectedRow()));
                }
//                System.out.println(orderList.get(orderTable.getSelectedRow()).getId());
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
                        ItemDetailGUI_2 itemDetailGUI_2 = new ItemDetailGUI_2(conn, item, card);
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
    private void setI4(){
        resetCard();
        try {
            imgDefault = ImageIO.read(new File("src/Res/test.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imgDefault = Utils.resize(imgDefault, 150, 150);

        nameL.setText(customer.getName());
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

    private void updateCardTable(){
        String[] labelT = new String[] {"Tên", "Số lượng", "Đơn giá (đồng)"};
        Object[][] data;
        if (card!=null && card.getOrderDetails() != null) {
            List<OrderDetail> orderDetails = card.getOrderDetails();
            data = new Object[orderDetails.size()][labelT.length];
            int cnt = 0;
            for (OrderDetail j : orderDetails) {
                Item item = Goods.getItemByID(conn, j.getIdItem());
                data[cnt][0] = item.getName();
                data[cnt][1] = j.getQuantity();
                data[cnt][2] = item.getPrice();
                cnt++;
            }
            priceAll.setText("Tổng tiền: " + Long.toString(card.getTotalPrice()));
        }else {
            data = new Object[][]{{"", "", ""}};
        }
        cardTable.setModel(new DefaultTableModel(data, labelT));
    }
    public Order getCard(){return card;}
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


}
