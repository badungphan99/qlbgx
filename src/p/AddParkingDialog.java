package p;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class AddParkingDialog extends JDialog {

	private final JPanel contentPane = new JPanel();;
	private JTextField txtParkingName, txtBicycleLot, txtMotorbikeLot, txtCarLot;
	private JLabel lblMessage, lblMessageTwo;
	private JComboBox<String> parkingActive;

	public AddParkingDialog(WorkFrame parent) {
			super(parent, "Add Parking", true);
			setAlwaysOnTop(true);

			// hien vi tri cua dialog o trong workframe, neu bo di thi dialog se o mot vi tri khac
			setBounds(440, 200, 600, 300);

			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));

			JLabel lblParkingName = new JLabel("Parking Name");
			contentPane.add(lblParkingName, "cell 0 0,alignx trailing");

			txtParkingName = new JTextField();
			contentPane.add(txtParkingName, "cell 1 0,growx");

			JLabel lblActive = new JLabel("Active");
			contentPane.add(lblActive, "cell 0 1,alignx trailing");

			String []active  = {"Yes", "No"};
			parkingActive = new JComboBox<String>(active);
			contentPane.add(parkingActive, "cell 1 1,growx");

			JLabel lblBicycleLot = new JLabel("Bicycle_lot");
			contentPane.add(lblBicycleLot, "cell 0 2,alignx trailing");

			txtBicycleLot = new JTextField();
			contentPane.add(txtBicycleLot, "cell 1 2,growx");

			JLabel lblMotorLot = new JLabel("Motorbike_lot");
			contentPane.add(lblMotorLot, "cell 0 3,alignx trailing");

			txtMotorbikeLot = new JTextField();
			contentPane.add(txtMotorbikeLot, "cell 1 3,growx");

			JLabel lblCarLot = new JLabel("Car_lot");
			contentPane.add(lblCarLot, "cell 0 4,alignx trailing");

			txtCarLot = new JTextField();
			contentPane.add(txtCarLot, "cell 1 4,growx");

			ParkingB parkingB = new ParkingB();
			Validation validation = new Validation();
			JButton btnAddParking = new JButton("Add");
			btnAddParking.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {	
						String activeSelectBox = parkingActive.getSelectedItem().toString();
						boolean active;
						if (activeSelectBox.equalsIgnoreCase("Yes")) {
							active = true;
						} else {
							active = false;
						}
						String name = txtParkingName.getText();
						int bicycleLot;
						int motorbikeLot;
						int carLot;
						if (parkingB.isNotExistParkingName(name)) {
								if (validation.isValidateInt(txtBicycleLot.getText()) && validation.isValidateInt(txtMotorbikeLot.getText()) && validation.isValidateInt(txtCarLot.getText())) {
									bicycleLot = Integer.parseInt(txtBicycleLot.getText());
									motorbikeLot = Integer.parseInt(txtMotorbikeLot.getText());
									carLot = Integer.parseInt(txtCarLot.getText());
									
									Parking parking = new Parking(0, name, active, bicycleLot, motorbikeLot, carLot);
									parkingB.addParking(parking);
									AddParkingDialog.this.dispose();
									JOptionPane.showMessageDialog(AddParkingDialog.this, "Successful!", "Add User",
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
							lblMessageTwo.setText("");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			lblMessage = new JLabel("Please enter information related to the parking");
			contentPane.add(lblMessage, "cell 1 5");

			lblMessageTwo = new JLabel("");
			contentPane.add(lblMessageTwo, "cell 1 6");

			contentPane.add(btnAddParking, "flowx,cell 1 7");
			JButton btnCancel = new JButton("Cancel");

			btnCancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					AddParkingDialog.this.dispose();
				}
			});
			contentPane.add(btnCancel, "cell 1 7");
		}

}
