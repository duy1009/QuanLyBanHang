package view;

import controller.*;
import view.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.List;

public class OrderDetailGUI {
    private JPanel panel1;
    private Connection conn;
    private Order order;
    private JLabel state;
    private JLabel priceAll;
    private JScrollPane Items;
    private JTable orderTable;
    private JButton cancelButton;
    private JButton confButton;
    private JLabel cusName;
    private JLabel addr;
    private JButton exit;
    private JLabel status;
    private JFrame jFrame;
    private Salesman salesman;
    public OrderDetailGUI(Connection conn, Order order, Salesman salesman){
        this.conn = conn;
        this.order = order;
        this.salesman = salesman;

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
        confButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Orders orders = new Orders(conn);
                if (orders.confOrder(salesman.getUname(), order.getId())){
                    status.setText("Đã duyệt đơn!");
                }else {
                    status.setText("Lỗi");
                }

            }
        });

        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Orders orders = new Orders(conn);
                if (orders.rejectOrder(salesman.getUname(), order.getId())){
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
