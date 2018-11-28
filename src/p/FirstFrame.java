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
        this.setSize(1024, 760);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        panel = new JPanel();
        this.setContentPane(contentPane);
        contentPane.setBackground(Color.WHITE);
        //this.setResizable(false);
       /* nameSystem = new JLabel("HỆ THỐNG QUẢN LÝ BÃI GỬI XE");
        Font font = new Font("Georgia", Font.BOLD, 20);
        nameSystem.setFont(font);
        nameSystem.setVerticalAlignment(SwingConstants.CENTER);
        nameSystem.setForeground(Color.black)*/;
       // contentPane.add(nameSystem);

        btnLogin = new JButton();
        btnLogin.setSize(1024,760);
       // contentPane.add(btnLogin);
        try {
            BufferedImage images = ImageIO.read(new File("image/baixe3.jpg"));
            ImageIcon icons = new ImageIcon(images.getScaledInstance(1024,768,images.SCALE_SMOOTH));
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

      contentPane.setLayout(new BorderLayout());
      contentPane.add(btnLogin, BorderLayout.PAGE_START);
      this.setResizable(false);
    }


}