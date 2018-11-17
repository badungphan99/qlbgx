package p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JLabel lblMessage;
	private JComboBox<String> usernameBox;
	
	
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

		UserB userB = new UserB();
		String [] usernames = userB.getAllUsername();
		usernameBox = new JComboBox<String>(usernames);
		contentPane.add(usernameBox, "cell 1 0,growx");
		
		
		JButton btnSelectUser = new JButton("OK");
		btnSelectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameBox.getSelectedItem().toString();
				EditUserDialog editUserDl = new EditUserDialog(parent, username);
				editUserDl.setVisible(true);
				SelectUserEditDialog.this.dispose();
			}
		});

		lblMessage = new JLabel("Please select username of the user that you want to edit!");
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
