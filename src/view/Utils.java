package view;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public final class Utils {
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    public static BufferedImage toBufferedImage(String bytesBase64)
            throws IOException {
        byte[] bytesFromDecode = Base64.decodeBase64(bytesBase64);
        InputStream is = new ByteArrayInputStream(bytesFromDecode);
        BufferedImage bi = ImageIO.read(is);
        return bi;

    }

    public static String toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        String bytesBase64 = Base64.encodeBase64String(bytes);
        return bytesBase64;

    }
    public static BufferedImage getBufferedImageFromIcon(Icon icon) {
        BufferedImage buffer = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();
        icon.paintIcon(new JLabel(), g, 0, 0);
        g.dispose();
        return buffer;
    }
    public static BufferedImage loadImage(String path) {
        BufferedImage img = null;
        InputStream is = Utils.class.getResourceAsStream(path);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    public static String Blob2String(Blob blob){
        byte[] bdata = new byte[0];
        try {
            bdata = blob.getBytes(1, (int) blob.length());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new String(bdata);
    }
    public static void printImage(BufferedImage img){
        for (int i=0;i<img.getHeight();i++){
            for (int j=0; j<img.getWidth();j++){
                System.out.print(img.getRGB(i, j));
            }
        }
    }
    public static String stateString(int state){
        String s ="";
        if (state == 0){
            s = "Chưa đặt";
        }
        else if (state == 1){
            s = "Đang chờ duyệt";
        }
        else if (state == 2){
            s = "Đã hoàn thành";
        }
        else if (state == 3){
            s = "Đơn bị hủy";
        }
        return s;
    }
}
