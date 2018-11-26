package p;
import java.io.FileInputStream;
import java.io.InputStream;

/*import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;*/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FirstFrame extends JFrame{
    private JLabel nameSystem;
    private JButton btnLogin;
    private JPanel contentPane;
    private JPanel mainPanel;
    private  JPanel panel;
    private JLabel label;

    public FirstFrame() {
        this.setTitle("Hệ thống quản lý bãi gửi xe");
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        panel = new JPanel();
        this.setContentPane(contentPane);
        contentPane.setBackground(Color.decode("#87CEEB"));
        contentPane.setBackground(Color.WHITE);
        nameSystem = new JLabel("HỆ THỐNG QUẢN LÝ BÃI GỬI XE");
        Font font = new Font("Georgia", Font.BOLD, 20);
        nameSystem.setFont(font);
        nameSystem.setVerticalAlignment(SwingConstants.CENTER);
        nameSystem.setForeground(Color.black);
        contentPane.add(nameSystem);

        btnLogin = new JButton("Login");
        contentPane.add(btnLogin);
        try {
            BufferedImage images = ImageIO.read(new File("image/icon.jpg"));
            ImageIcon icons = new ImageIcon(images.getScaledInstance(20,20,images.SCALE_SMOOTH));
            btnLogin.setIcon(icons);
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LoginDialog lDialog = new LoginDialog(FirstFrame.this);
                lDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                lDialog.setVisible(true);

            }
        });
       label = new JLabel();
       label.setSize(1000,300);
        try {
            BufferedImage image = ImageIO.read(new File("image/baixe3.jpg"));
            ImageIcon icon = new ImageIcon(image.getScaledInstance(1490,720,image.SCALE_SMOOTH));
            label.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.setBackground(Color.WHITE);
        panel.add(label);
        contentPane.add(panel, BorderLayout.SOUTH);
    }


}