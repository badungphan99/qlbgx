package p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JComboBox<String> employeeIdBox;
	private JCheckBox changeUsername, notChangeUsername;

	public SelectUserEditDialog(WorkFrame parent) {
		super(parent, "Selec User Edit", true);
		// setAlwaysOnTop(true);

		// hien vi tri cua dialog so voi workframe, neu bo di thi dialog se o mot vi tri
		// khac khong o nam trong vi tri cua workframe
		setBounds(100, 100, 520, 200);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));

		lblMessage = new JLabel("Please select id of the user that you want to edit!");
		contentPane.add(lblMessage, "cell 1 0");

		JLabel lblEmployeeID = new JLabel("Employee ID");
		contentPane.add(lblEmployeeID, "cell 0 1,alignx trailing");

		UserB userB = new UserB();

		String[] employeeIds = userB.getAllEmployeeID();
		employeeIdBox = new JComboBox<String>(employeeIds);
		contentPane.add(employeeIdBox, "cell 1 1,growx");

		JButton btnSelectUser = new JButton("OK");
		btnSelectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(employeeIdBox.getSelectedItem().toString());
				String username = userB.getUsernameBySelectId(id);
				int n = JOptionPane.showConfirmDialog(parent, "Do you want to change username of this user?",
						"Select Change Username", JOptionPane.YES_NO_OPTION);

				// chọn Yes thì mở dialog có phần nhập username, chon No thì mở dialog ko có
				// phần nhập username và giữ nguyên username tương ứng với id đã chọn
				if (n == JOptionPane.YES_OPTION) {
					EditUserDialog editUserDl = new EditUserDialog(parent, id);
					editUserDl.setVisible(true);
				} else {
					EditUserNotChangeUsernameDialog edtUsrNotChangeUsernameDl = new EditUserNotChangeUsernameDialog(parent, id,
							username);
					edtUsrNotChangeUsernameDl.setVisible(true);
				}

				SelectUserEditDialog.this.dispose();
			}
		});

		contentPane.add(btnSelectUser, "flowx,cell 1 2");

		JButton btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SelectUserEditDialog.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 2");
	}
}
