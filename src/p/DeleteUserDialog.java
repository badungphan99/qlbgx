package p;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import b.UserB;
import e.User;
import net.miginfocom.swing.MigLayout;
public class DeleteUserDialog  extends JDialog{
	private final JPanel contentPane = new JPanel();
	private JLabel lblMessage;
	private JComboBox<String> usernameBox;
	
	public DeleteUserDialog(WorkFrame parent) {
		super(parent, "Delete User", true);
		setAlwaysOnTop(true);

		// hien vi tri cua dialog so voi workframe, neu bo di thi dialog se o mot vi tri
		// khac khong o nam trong vi tri cua workframe
		setBounds(100, 100, 600, 200);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));

		JLabel lblemployeeID = new JLabel("Username");
		contentPane.add(lblemployeeID, "cell 0 0,alignx trailing");

		UserB userB = new UserB();
		String [] usernames = userB.getAllUsername();
		usernameBox = new JComboBox<String>(usernames);
		contentPane.add(usernameBox, "cell 1 0,growx");

		JButton btnDeleteUser = new JButton("Delete");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserB userB = new UserB();
				String username = usernameBox.getSelectedItem().toString();
				int n = JOptionPane.showConfirmDialog(DeleteUserDialog.this,
						"Are you sure you want to delete user '" + username + "' ?", "Confirm User Delete",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					userB.DeleteUser(username);
					DeleteUserDialog.this.dispose();
				}
			}
		});

		lblMessage = new JLabel("Please select username of the user that you want to delete!");
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
