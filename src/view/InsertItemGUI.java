package view;

import controller.Goods;
import controller.Item;
import view.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class InsertItemGUI {
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea textArea1;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox nsx2;
    private JComboBox nsx3;
    private JComboBox nsx1;
    private JComboBox hsd2;
    private JComboBox hsd3;
    private JComboBox hsd1;
    private JButton imageB;
    private JButton applyB;
    private JPanel panel1;
    private JButton xButton;
    private JButton UploadImage;
    private JLabel stateL;
    Connection conn;
    BufferedImage img;
    public InsertItemGUI(Connection conn) {
        JFrame jFrame = new JFrame("Insert");
        jFrame.setContentPane(this.panel1);
        jFrame.setUndecorated(true);
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


        img = new BufferedImage(150, 150, TYPE_INT_RGB);

        UploadImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setDialogTitle("Choose image");
                jfc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and JPG images", "png", "jpeg");
                jfc.addChoosableFileFilter(filter);
                int returnValue = jfc.showDialog(null, "Choose");
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        img = ImageIO.read(new File(jfc.getSelectedFile().getPath()));
                        img = Utils.resize(img, 150, 150);
                        imageB.setIcon(new ImageIcon(img));

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }

            }
        });
        applyB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Goods goods = new Goods(conn);
                if (goods.addItem(
                        textField1.getText(),
                        textArea1.getText(),
                        Long.parseLong(textField4.getText()),
                        Integer.parseInt(textField2.getText()),
                        Integer.parseInt(textField3.getText()),
                        getDate(nsx1,nsx2,nsx3),
                        getDate(hsd1, hsd2, hsd3),
                        img
                )){
                    stateL.setText("Đã thêm sản phẩm");
                }else {
                    stateL.setText("Sản phẩm đã tồn tại");
                }

            }
        });
    }

    private String getDate(JComboBox day, JComboBox month, JComboBox year){
        return year.getSelectedItem() +"-"+ month.getSelectedItem() +"-"+ day.getSelectedItem();
    }

}
