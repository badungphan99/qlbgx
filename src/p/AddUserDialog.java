package p;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import b.UserB;
import e.User;
import net.miginfocom.swing.MigLayout;

public class AddUserDialog extends JDialog {
	private final JPanel contentPane = new JPanel();;
	private JTextField txtUsername, txtEmail, txtFullname, txtRole, txtParkingId;
	private JPasswordField txtPassword;
	private JLabel lblMessage;

	public AddUserDialog(WorkFrame parent) {
		super(parent, "Add User", true);
		setAlwaysOnTop(true);

		// hien vi tri cua dialog so voi workframe, neu bo di thi dialog se o mot vi tri
		// khac khong o nam trong vi tri cua workframe
		setBounds(100, 100, 450, 300);

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

		JLabel lblEmail = new JLabel("Email");
		contentPane.add(lblEmail, "cell 0 2,alignx trailing");

		txtEmail = new JTextField();
		contentPane.add(txtEmail, "cell 1 2,growx");

		JLabel lblFullname = new JLabel("Fullname");
		contentPane.add(lblFullname, "cell 0 3,alignx trailing");

		txtFullname = new JTextField();
		contentPane.add(txtFullname, "cell 1 3,growx");

		JLabel lblRole = new JLabel("Role");
		contentPane.add(lblRole, "cell 0 4,alignx trailing");

		txtRole = new JTextField();
		contentPane.add(txtRole, "cell 1 4,growx");

		JLabel lblParkingId = new JLabel("Parking ID");
		contentPane.add(lblParkingId, "cell 0 5,alignx trailing");

		txtParkingId = new JTextField();
		contentPane.add(txtParkingId, "cell 1 5,growx");

		JButton btnAddUser = new JButton("Add");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UserB userB = new UserB();
					int role = Integer.parseInt(txtRole.getText());
					int parkingId = Integer.parseInt(txtParkingId.getText());
					if (userB.checkUserExist(txtUsername.getText())) {
						User user = new User(0, txtUsername.getText(), txtPassword.getText(), txtEmail.getText(), txtFullname.getText(), role, parkingId);
						userB.AddUser(user);
						JOptionPane.showMessageDialog(AddUserDialog.this, "Success!", "Add User", JOptionPane.INFORMATION_MESSAGE);
						AddUserDialog.this.dispose();
					} else {
						lblMessage.setForeground(Color.RED);
						lblMessage.setText("Username is already taken");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		lblMessage = new JLabel("Please enter information related to the user");
		contentPane.add(lblMessage, "cell 1 6");
		contentPane.add(btnAddUser, "flowx,cell 1 7");

		JButton btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddUserDialog.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 7");
	}

}
