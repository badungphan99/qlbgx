package p;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import b.UserB;
import e.User;
import net.miginfocom.swing.MigLayout;
public class DeleteUserDialog  extends JDialog{
	private final JPanel contentPane = new JPanel();;
	private JTextField txtUsername;
	private JLabel lblMessage;
	
	public DeleteUserDialog(WorkFrame parent) {
		super(parent, "Delete User", true);
		setAlwaysOnTop(true);

		// hien vi tri cua dialog so voi workframe, neu bo di thi dialog se o mot vi tri
		// khac khong o nam trong vi tri cua workframe
		setBounds(100, 100, 600, 100);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));

		JLabel lblemployeeID = new JLabel("Username");
		contentPane.add(lblemployeeID, "cell 0 0,alignx trailing");

		
		txtUsername = new JTextField();
		contentPane.add(txtUsername, "cell 1 0,growx");

		JButton btnDeleteUser = new JButton("Delete");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UserB userB = new UserB();
					if (!userB.checkUserExist(txtUsername.getText())) {
						String username = txtUsername.getText();
						int n = JOptionPane.showConfirmDialog(DeleteUserDialog.this, "Delete user "  + txtUsername.getText() + " ?", "Confirm delete user", JOptionPane.YES_NO_CANCEL_OPTION);
						if (n == JOptionPane.YES_OPTION) {
							userB.DeleteUser(username);
							DeleteUserDialog.this.dispose();
						}
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

		lblMessage = new JLabel("Please enter username of the user that you want delete!");
		contentPane.add(lblMessage, "cell 1 1");
		contentPane.add(btnDeleteUser, "flowx,cell 1 2");

		JButton btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DeleteUserDialog.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 2");
	}
}
