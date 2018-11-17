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
import net.miginfocom.swing.MigLayout;

public class SelectUserEditDialog extends JDialog {
	private final JPanel contentPane = new JPanel();;
	private JTextField txtUsername;
	private JLabel lblMessage;

	
	
	public SelectUserEditDialog(WorkFrame parent) {
		super(parent, "Selec User Edit", true);
		//setAlwaysOnTop(true);

		// hien vi tri cua dialog so voi workframe, neu bo di thi dialog se o mot vi tri
		// khac khong o nam trong vi tri cua workframe
		setBounds(100, 100, 520, 200);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));

		JLabel lblUsername = new JLabel("Username");
		contentPane.add(lblUsername, "cell 0 0,alignx trailing");

		
		txtUsername = new JTextField();
		contentPane.add(txtUsername, "cell 1 0,growx");
		
		
		JButton btnSelectUser = new JButton("OK");
		btnSelectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UserB userB = new UserB();
					if (!userB.checkUserExist(txtUsername.getText())) {
						EditUserDialog editUserDl = new EditUserDialog(parent, txtUsername.getText());
						editUserDl.setVisible(true);
						SelectUserEditDialog.this.dispose();
					} else {
						lblMessage.setForeground(Color.RED);
						lblMessage.setText("Username is not exist!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		lblMessage = new JLabel("Please enter username of the user that you want to edit!");
		contentPane.add(lblMessage, "cell 1 2");
		contentPane.add(btnSelectUser, "flowx,cell 1 3");

		JButton btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SelectUserEditDialog.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 3");
	}
}
