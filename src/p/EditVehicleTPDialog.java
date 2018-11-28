package p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import b.Validation;
import b.VehicleTypePriceB;
import net.miginfocom.swing.MigLayout;

public class EditVehicleTPDialog extends JDialog {
	private final JPanel contentPane = new JPanel();
	private JTextField txtPrice, txtTimePerios, txtMode, txtPerHour, txtPerDay;
	private JLabel lblMessageOne, lblMessageTwo;

	public EditVehicleTPDialog(WorkFrame parent, int id) {
		super(parent, "Edit Vehicle Type Price", true);
		setAlwaysOnTop(true);

		setBounds(440, 200, 600, 300);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));

		JLabel lblPrice = new JLabel("Price");
		contentPane.add(lblPrice, "cell 0 0,alignx trailing");

		txtPrice = new JTextField();
		contentPane.add(txtPrice, "cell 1 0,growx");

		JLabel lblTimePerios = new JLabel("Time Perios");
		contentPane.add(lblTimePerios, "cell 0 1,alignx trailing");

		txtTimePerios = new JTextField();
		contentPane.add(txtTimePerios, "cell 1 1,growx");

		JLabel lblMode = new JLabel("Mode");
		contentPane.add(lblMode, "cell 0 2,alignx trailing");

		txtMode = new JTextField();
		contentPane.add(txtMode, "cell 1 2,growx");

		JLabel lblPerHour = new JLabel("Per hour");
		contentPane.add(lblPerHour, "cell 0 3,alignx trailing");

		txtPerHour = new JTextField();
		contentPane.add(txtPerHour, "cell 1 3,growx");

		JLabel lblPerDay = new JLabel("Per day");
		contentPane.add(lblPerDay, "cell 0 4,alignx trailing");
		
		txtPerDay = new JTextField();
		contentPane.add(txtPerDay, "cell 1 4,growx" );
		
		
		Validation validation = new Validation();
		VehicleTypePriceB vehicleTPB = new VehicleTypePriceB();
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int price;
				int parkingPerios;
				int mode;
				int perHour;
				int perDay;
				if (validation.isValidateInt(txtPrice.getText())
						&& validation.isValidateInt(txtTimePerios.getText())
						&& validation.isValidateInt(txtMode.getText())) {
					price = Integer.parseInt(txtPrice.getText());
					parkingPerios = Integer.parseInt(txtTimePerios.getText());
					mode = Integer.parseInt(txtMode.getText());
					perHour = Integer.parseInt(txtPerHour.getText());
					perDay = Integer.parseInt(txtPerDay.getText());
					
					vehicleTPB.insertVehicleSetting(id, price, parkingPerios, perHour, perDay);
					
					EditVehicleTPDialog.this.dispose();
					JOptionPane.showMessageDialog(EditVehicleTPDialog.this, "Successful!", "Edit Parking",
							JOptionPane.INFORMATION_MESSAGE);

				} else {
					lblMessageOne.setForeground(Color.RED);
					lblMessageOne.setText("Not validate! All textField must have 1-15 character");
					lblMessageTwo.setForeground(Color.RED);
					lblMessageTwo.setText("and mustn't have non-numeric character.");
				}
			}
		});

		lblMessageOne = new JLabel("Please enter information related to the vehicle type price");
		contentPane.add(lblMessageOne, "cell 1 5");

		lblMessageTwo = new JLabel("");
		contentPane.add(lblMessageTwo, "cell 1 6");

		contentPane.add(btnEdit, "flowx,cell 1 7");

		JButton btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditVehicleTPDialog.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 7");
	}
}
