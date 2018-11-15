package p;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FirstFrame extends JFrame{
	private JLabel nameSystem;
	private JButton btnLogin;
	private JPanel contentPane;

	public FirstFrame() {
		this.setTitle("Hệ thống quản lý bãi gửi xe");
		this.setSize(600, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		contentPane = new JPanel();
		this.setContentPane(contentPane);
		contentPane.setBackground(Color.BLUE);
		
		nameSystem = new JLabel("HỆ THỐNG QUẢN LÝ BÃI GỬI XE");
		Font font = new Font("Arial", Font.BOLD, 30);
		nameSystem.setFont(font);
		nameSystem.setVerticalAlignment(JLabel.CENTER);
		nameSystem.setForeground(Color.YELLOW);
		this.add(nameSystem);
		
		btnLogin = new JButton("Login");
		this.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginDialog lDialog = new LoginDialog(FirstFrame.this);
				lDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				lDialog.setVisible(true);
				
			}
		});
		
	}
}
