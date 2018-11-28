package p;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import b.ParkingB;
import b.UserB;
import e.User;
import net.miginfocom.swing.MigLayout;

public class EditUserNotChangeUsernameDialog extends JDialog {
	private final JPanel contentPane = new JPanel();;
	private JTextField txtEmail, txtFullname;
	private JLabel lblMessage;
	private JComboBox <String> parkingIdBox, roleBox;
	public EditUserNotChangeUsernameDialog(WorkFrame parent, int id, String usernameEdit) {
		super(parent, "Edit User", true);
		setAlwaysOnTop(true);

		setBounds(440, 300, 450, 200);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));

		JLabel lblEmail = new JLabel("Email");
		contentPane.add(lblEmail, "cell 0 0,alignx trailing");

		txtEmail = new JTextField();
		contentPane.add(txtEmail, "cell 1 0,growx");

		JLabel lblFullname = new JLabel("Fullname");
		contentPane.add(lblFullname, "cell 0 1,alignx trailing");

		txtFullname = new JTextField();
		contentPane.add(txtFullname, "cell 1 1,growx");

		JLabel lblRole = new JLabel("Role");
		contentPane.add(lblRole, "cell 0 2,alignx trailing");

		String [] role = {"Employee", "Boss"};
		roleBox = new JComboBox<String>(role);
		contentPane.add(roleBox, "cell 1 2,growx");

		JLabel lblParkingId = new JLabel("Parking ID");
		contentPane.add(lblParkingId, "cell 0 3,alignx trailing");

		ParkingB parkingB = new ParkingB();
		String [] parkingId = parkingB.getAllParkingIdActive(true);
		parkingIdBox = new JComboBox<String>(parkingId);
		contentPane.add(parkingIdBox, "cell 1 3,growx");

		JButton btnEditUser = new JButton("Edit");
		btnEditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserB userB = new UserB();
				String roleSelectBox = roleBox.getSelectedItem().toString();
				int role;
				if (roleSelectBox.equalsIgnoreCase("Boss")){
					role = 0;
				}else {
					role = 1;
				}
				int parkingId = Integer.parseInt((parkingIdBox.getSelectedItem().toString()));

				User user = new User(id, usernameEdit, "", true, txtEmail.getText(), txtFullname.getText(), role,
						parkingId);
				
				userB.EditUser(user);
				EditUserNotChangeUsernameDialog.this.dispose();
				JOptionPane.showMessageDialog(parent, "Success!", "Edit User", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		lblMessage = new JLabel("Please enter information related to the user");
		contentPane.add(lblMessage, "cell 1 4");
		contentPane.add(btnEditUser, "flowx,cell 1 5");

		JButton btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditUserNotChangeUsernameDialog.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 5");
	}

}
