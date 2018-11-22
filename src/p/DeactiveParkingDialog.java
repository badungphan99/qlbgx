package p;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import b.ParkingB;
import net.miginfocom.swing.MigLayout;

public class DeactiveParkingDialog extends JDialog {
	private final JPanel contentPane = new JPanel();
	private JLabel lblMessage;
	private JComboBox<String> parkingIdBox;

	public DeactiveParkingDialog(WorkFrame parent) {
		super(parent, "Deactive Parking", true);
		setAlwaysOnTop(true);

		// hien vi tri cua dialog so voi workframe, neu bo di thi dialog se o mot vi tri
		// khac khong o nam trong vi tri cua workframe
		setBounds(100, 100, 600, 200);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));

		JLabel lblparkingID = new JLabel("ID");
		contentPane.add(lblparkingID, "cell 0 0,alignx trailing");

		ParkingB parkingB = new ParkingB();
		String[] parkingIds = parkingB.getAllParkingIdActive();
		parkingIdBox = new JComboBox<String>(parkingIds);
		contentPane.add(parkingIdBox, "cell 1 0,growx");

		JButton btnDeactiveParking = new JButton("Deactive");
		btnDeactiveParking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String parkingId = parkingIdBox.getSelectedItem().toString();
				int id = Integer.parseInt(parkingId.trim());
				int n = JOptionPane.showConfirmDialog(DeactiveParkingDialog.this,
						"Are you sure you want to deactive parking '" + parkingId + "' ?", "Confirm Parking Deactive",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					parkingB.deactiveParking(id);
					DeactiveParkingDialog.this.dispose();
				}
			}
		});

		lblMessage = new JLabel("Please select id of the parking that you want to deactive!");
		contentPane.add(lblMessage, "cell 1 1");
		contentPane.add(btnDeactiveParking, "flowx,cell 1 2");

		JButton btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DeactiveParkingDialog.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 2");
	}
}
