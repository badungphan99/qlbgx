package p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import b.UserB;
import e.User;
import b.loginSession;
import net.miginfocom.swing.MigLayout;


public class LoginDialog extends JDialog{
	private final JPanel contentPane = new JPanel();;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	private JLabel lblMessage;
	
	
	public LoginDialog(FirstFrame parent) {

		super(parent, "Login", true);
		setAlwaysOnTop(true);
		setBounds(300, 200, 500, 200);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
		
		JLabel lblUsername = new JLabel("Username");
		contentPane.add(lblUsername, "cell 0 0,alignx trailing");
		
		txtUsername = new JTextField();
		contentPane.add(txtUsername, "cell 1 0,growx");
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		contentPane.add(lblPassword, "cell 0 1,alignx trailing");
		
		txtPassword = new JPasswordField();
		contentPane.add(txtPassword, "cell 1 1,growx");
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UserB userB = new UserB();
					String username = txtUsername.getText();
					String password = txtPassword.getText();
					User user = userB.checkLogin(username, password);
					if(user.getRole() != -1 && user.isActive()){
						loginSession.setUser(user);
						WorkFrame bossFrame = new WorkFrame(parent);
						bossFrame.setVisible(true);
						parent.setVisible(false);
						if (userB.checkBossLogin(txtUsername.getText(), txtPassword.getText())) {
							bossFrame.enableControl();
						}
						LoginDialog.this.dispose();
					}else{
						lblMessage.setForeground(Color.RED);
						lblMessage.setText("Incorect username or password!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		lblMessage = new JLabel("Please enter your username and password");
		contentPane.add(lblMessage, "cell 1 2");
		contentPane.add(btnLogin, "flowx,cell 1 3");
		
		JButton btnCancel = new JButton("Cancel");
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginDialog.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 3");
	}
	
}
