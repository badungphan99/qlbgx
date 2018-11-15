package p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import b.InforB;
import net.miginfocom.swing.MigLayout;

public class CheckInDialog extends JDialog {
	private JPanel contentPane;
	private JTextField txtcardid, txttype, txtlicenseplate;
	private WorkFrame boss;
	private JLabel lblMessage;
	private static CheckInDialog checkIn;
	private InforB inforB = new InforB();

	public static CheckInDialog getCheckInFrame() {
		return checkIn;
	}

	public CheckInDialog(WorkFrame bossframe) {
		super(bossframe, "Check In", true);
		setAlwaysOnTop(true);

	
		this.setSize(600, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
		
		JLabel lblcardid = new JLabel("Card ID");
		contentPane.add(lblcardid, "cell 0 0,alignx trailing");
		
		txtcardid = new JTextField();
		contentPane.add(txtcardid, "cell 1 0,growx");
		
		txtcardid.setColumns(10);
		
		JLabel lbltype = new JLabel("Vehicle Type");
		contentPane.add(lbltype, "cell 0 1,alignx trailing");
		
		txttype = new JTextField();
		contentPane.add(txttype, "cell 1 1,growx");
		
		JLabel lbllicense = new JLabel("License Plate");
		contentPane.add(lbllicense, "cell 0 2,alignx trailing");
		
		txtlicenseplate = new JTextField();
		contentPane.add(txtlicenseplate, "cell 1 2,growx");
		
		JButton btnAdd = new JButton("Add");
		
		
		lblMessage = new JLabel("Please enter vehicle type and license plate");
		contentPane.add(lblMessage, "cell 1 3");
		contentPane.add(btnAdd, "flowx,cell 1 4");

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				inforB.checkin(txttype.getText(),txtlicenseplate.getText());
				CheckInDialog.this.setVisible(false);
			}
		});


		JButton btnCancel = new JButton("Cancel");
		contentPane.add(btnCancel, "cell 1 4");

		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckInDialog.this.setVisible(false);
				
			}
		});
	}
}
