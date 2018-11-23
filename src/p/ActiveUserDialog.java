package p;

import javax.swing.JDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import b.UserB;
import net.miginfocom.swing.MigLayout;

public class ActiveUserDialog extends JDialog {
	private final JPanel contentPane = new JPanel();
	private JLabel lblMessage;
	private JComboBox<String> employeeIdBox;

	public ActiveUserDialog(WorkFrame parent, String title, boolean active) {
		super(parent, title + " User", true);
		setAlwaysOnTop(true);

		// hien vi tri cua dialog so voi workframe, neu bo di thi dialog se o mot vi tri
		// khac khong o nam trong vi tri cua workframe
		setBounds(100, 100, 600, 200);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));

		JLabel lblemployeeID = new JLabel("ID");
		contentPane.add(lblemployeeID, "cell 0 0,alignx trailing");

		UserB userB = new UserB();
		String[] employeeIds = userB.getAllEmployeeIDActive(!active);
		employeeIdBox = new JComboBox<String>(employeeIds);
		contentPane.add(employeeIdBox, "cell 1 0,growx");

		JButton btnActiveUser = new JButton(title);
		btnActiveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserB userB = new UserB();
				String employeeId = employeeIdBox.getSelectedItem().toString();
				int id = Integer.parseInt(employeeId.trim());
				int n = JOptionPane.showConfirmDialog(ActiveUserDialog.this,
						"Are you sure you want to " + title.toLowerCase() + " user '" + employeeId + "' ?", "Confirm User " + title,
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					userB.activeUser(id, active);
					ActiveUserDialog.this.dispose();
				}
			}
		});

		lblMessage = new JLabel("Please select id of the user that you want to " + title.toLowerCase() + "!");
		contentPane.add(lblMessage, "cell 1 1");
		contentPane.add(btnActiveUser, "flowx,cell 1 2");

		JButton btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ActiveUserDialog.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 2");
	}

}
