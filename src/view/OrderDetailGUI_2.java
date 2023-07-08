package view;

import controller.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.List;

public class OrderDetailGUI_2 {
    private JPanel panel1;
    private JLabel cusName;
    private JLabel state;
    private JLabel priceAll;
    private JTable orderTable;
    private JButton cancelButton;
    private JLabel addr;
    private JButton exit;
    private JLabel status;
    private Connection conn;
    private Order order;
    private JFrame jFrame;
    public OrderDetailGUI_2(Connection conn, Order order){
        this.conn = conn;
        this.order = order;

        jFrame = new JFrame("Order Detail");
        init();
        jFrame.setContentPane(panel1);
        jFrame.setUndecorated(true);
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

    }
    private void init(){
//        System.out.println("AAAAAAAAAAAAA" + order.getCustomer_username());
        Customer customer = Customer.getCustomerByUN(conn, order.getCustomer_username());
        cusName.setText(customer.getName());
        addr.setText(customer.getAddress());
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jFrame.setVisible(false);
            }
        });

        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Orders orders = new Orders(conn);
                if (orders.rejectOrder("",order.getId())){
                    status.setText("Đã hủy đơn!");
                }else {
                    status.setText("Lỗi");
                }
            }
        });
        initItemTable();
    }
    private void initItemTable(){
        String[] labelT = new String[] {"Tên", "Số lượng", "Đơn giá (đồng)"};
        List<OrderDetail> orderDetails = order.getOrderDetails();
        Object[][] data = new Object[orderDetails.size()][labelT.length];
        int cnt = 0;
        for (OrderDetail j :orderDetails){
            Item item = Goods.getItemByID(conn, j.getIdItem());
            data[cnt][0] = item.getName();
            data[cnt][1] = j.getQuantity();
            data[cnt][2] = item.getPrice();
            cnt++;
        }
        state.setText(Utils.stateString(order.getState()));
        priceAll.setText(Long.toString(order.getTotalPrice()));
        orderTable.setModel(new DefaultTableModel(data, labelT));
    }
}
