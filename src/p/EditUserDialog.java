package p;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import b.ParkingB;
import b.UserB;
import e.User;
import net.miginfocom.swing.MigLayout;

public class EditUserDialog extends JDialog {
	private final JPanel contentPane = new JPanel();;
	private JTextField txtUsername, txtEmail, txtFullname;
	private JLabel lblMessage, lblMessageTwo;
	private JComboBox <String> parkingIdBox, roleBox;

	public EditUserDialog(WorkFrame parent, int id) {
		super(parent, "Edit User", true);
		setAlwaysOnTop(true);

		// hien vi tri cua dialog so voi workframe, neu bo di thi dialog se o mot vi tri
		// khac khong o nam trong vi tri cua workframe
		setBounds(100, 100, 550, 300);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
		
		JLabel lblUsername = new JLabel("Username");
		contentPane.add(lblUsername, "cell 0 0,alignx trailing");
		
		txtUsername = new JTextField();
		contentPane.add(txtUsername, "cell 1 0,growx");
		
		JLabel lblEmail = new JLabel("Email");
		contentPane.add(lblEmail, "cell 0 1,alignx trailing");

		txtEmail = new JTextField();
		contentPane.add(txtEmail, "cell 1 1,growx");

		JLabel lblFullname = new JLabel("Fullname");
		contentPane.add(lblFullname, "cell 0 2,alignx trailing");

		txtFullname = new JTextField();
		contentPane.add(txtFullname, "cell 1 2,growx");

		JLabel lblRole = new JLabel("Role");
		contentPane.add(lblRole, "cell 0 3,alignx trailing");

		String [] role = {"Employee", "Boss"};
		roleBox = new JComboBox<String>(role);
		contentPane.add(roleBox, "cell 1 3,growx");

		JLabel lblParkingId = new JLabel("Parking ID");
		contentPane.add(lblParkingId, "cell 0 4,alignx trailing");

		ParkingB parkingB = new ParkingB();
		String [] parkingId = parkingB.getAllParkingIdActive(true);
		parkingIdBox = new JComboBox<String>(parkingId);
		contentPane.add(parkingIdBox, "cell 1 4,growx");

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
				String username = txtUsername.getText();
				
				//kiểm tra username mới có trùng với các username khác ngoài username cũ của user muốn edit
				try {
					// Xử lý xong phần check name rồi nhé
		//			System.out.println(userB.validate(username));

					if (userB.checkEditUsername(id, username)) {
						if (userB.isValidate(username)) {
							User user = new User(id, username, "", true, txtEmail.getText(), txtFullname.getText(), role,
									parkingId);
							
							userB.EditUser(user);
							EditUserDialog.this.dispose();
							JOptionPane.showMessageDialog(parent, "Success!", "Edit User", JOptionPane.INFORMATION_MESSAGE);
						}else {
							lblMessage.setForeground(Color.RED);
							lblMessage.setText("Username is not validate! It must have 3-15 characters ");
							lblMessageTwo.setForeground(Color.RED);
							lblMessageTwo.setText("and mustn't have non-alphanumeric character!");
						}
					} else {
						lblMessage.setForeground(Color.RED);
						lblMessage.setText("Username is already taken!");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		lblMessage = new JLabel("Please enter information related to the user");
		contentPane.add(lblMessage, "cell 1 5");
		
		lblMessageTwo = new JLabel("");
		contentPane.add(lblMessageTwo, "cell 1 6");
		
		contentPane.add(btnEditUser, "flowx,cell 1 7");

		JButton btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditUserDialog.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 7");
	}

}

