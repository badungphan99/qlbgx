package p;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import b.ParkingB;
import b.Validation;
import e.Parking;
import net.miginfocom.swing.MigLayout;

public class EditParkingDialog extends JDialog {
	private final JPanel contentPane = new JPanel();;
	private JTextField txtParkingName, txtBicycleLot, txtMotorbikeLot, txtCarLot;
	private JLabel lblMessage, lblMessageTwo;

	public EditParkingDialog(WorkFrame parent, int id) {
		super(parent, "Edit Parking", true);
		setAlwaysOnTop(true);

		setBounds(300, 200, 600, 300);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));

		JLabel lblParkingName = new JLabel("Parking_name");
		contentPane.add(lblParkingName, "cell 0 0,alignx trailing");

		txtParkingName = new JTextField();
		contentPane.add(txtParkingName, "cell 1 0,growx");

		JLabel lblBicycleLot = new JLabel("Bicycle_lot");
		contentPane.add(lblBicycleLot, "cell 0 1,alignx trailing");

		txtBicycleLot = new JTextField();
		contentPane.add(txtBicycleLot, "cell 1 1,growx");

		JLabel lblMotorbikeLot = new JLabel("Motorbike_lot");
		contentPane.add(lblMotorbikeLot, "cell 0 2,alignx trailing");

		txtMotorbikeLot = new JTextField();
		contentPane.add(txtMotorbikeLot, "cell 1 2,growx");

		JLabel lblCarLot = new JLabel("Car_lot");
		contentPane.add(lblCarLot, "cell 0 3,alignx trailing");

		txtCarLot = new JTextField();
		contentPane.add(txtCarLot, "cell 1 3,growx");

		ParkingB parkingB = new ParkingB();
		Validation validation = new Validation();
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String parkingName = txtParkingName.getText();
				int bicycleLot;
				int motorbikeLot;
				int carLot;
				// kiểm tra parkingname mới có trùng với các parkingname khác ngoài parkingname cũ của
				// parking muốn edit
				try {
					if (parkingB.checkEditParkingName(id, parkingName)) {
						if (validation.isValidateParkingLot(txtBicycleLot.getText())
								&& validation.isValidateParkingLot(txtMotorbikeLot.getText())
								&& validation.isValidateParkingLot(txtCarLot.getText())) {
							bicycleLot = Integer.parseInt(txtBicycleLot.getText());
							motorbikeLot = Integer.parseInt(txtMotorbikeLot.getText());
							carLot = Integer.parseInt(txtCarLot.getText());

							Parking parking = new Parking(id, parkingName, true, bicycleLot, motorbikeLot, carLot);
							parkingB.editParking(parking);
							EditParkingDialog.this.dispose();
							JOptionPane.showMessageDialog(EditParkingDialog.this, "Successful!", "Edit Parking",
									JOptionPane.INFORMATION_MESSAGE);

						} else {
							lblMessage.setForeground(Color.RED);
							lblMessage.setText("Bicycle_lot or Motorbike_lot or Car_lot is not validate! It must");
							lblMessageTwo.setForeground(Color.RED);
							lblMessageTwo.setText("have 1-15 character and mustn't have non-numeric character.");
						}

					} else {
						lblMessage.setForeground(Color.RED);
						lblMessage.setText("Parking_name is already taken!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		lblMessage = new JLabel("Please enter information related to the parking");
		contentPane.add(lblMessage, "cell 1 4");

		lblMessageTwo = new JLabel("");
		contentPane.add(lblMessageTwo, "cell 1 5");

		contentPane.add(btnEdit, "flowx,cell 1 6");

		JButton btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditParkingDialog.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 6");
	}
}
