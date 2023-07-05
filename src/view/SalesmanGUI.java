package view;

import controller.Salesman;

import javax.swing.*;

public class SalesmanGUI {
    private JPanel panel1;
    private JButton đăngXuấtButton;
    private JButton cậpNhậtButton;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JButton tìmKiếmButton;
    private JSpinner spinner1;
    private JButton thêmSảnPhẩmButton;
    private JButton chỉnhSửaButton;
    private JButton xemChiTiếtButton;
    private JTable table1;
    Salesman salesman;
    public SalesmanGUI(Salesman salesman){
        this.salesman =salesman;
    }
}
