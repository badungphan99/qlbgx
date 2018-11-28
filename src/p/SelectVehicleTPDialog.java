package p;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import b.ParkingB;
import b.VehicleTypePriceB;
import net.miginfocom.swing.MigLayout;

public class SelectVehicleTPDialog extends JDialog{
	private final JPanel contentPane = new JPanel();;
	private JLabel lblMessage;
	private JComboBox<String> IdBox;

	public SelectVehicleTPDialog(WorkFrame parent) {
		super(parent, "Select Vehicle Type Price Edit", true);
		// setAlwaysOnTop(true);

		setBounds(440, 200, 520, 180);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));

		lblMessage = new JLabel("Please select id of the vehicle that you want to edit!");
		contentPane.add(lblMessage, "cell 1 0");

		JLabel lblEmployeeID = new JLabel("Vehicle ID");
		contentPane.add(lblEmployeeID, "cell 0 1,alignx trailing");

		VehicleTypePriceB vehicleTPB = new VehicleTypePriceB();

		String[] Ids = vehicleTPB.getAllVehicleTPID();
		IdBox = new JComboBox<String>(Ids);
		contentPane.add(IdBox, "cell 1 1,growx");

		JButton btnSelect = new JButton("OK");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(IdBox.getSelectedItem().toString());
				
				EditVehicleTPDialog editVehicleDl = new EditVehicleTPDialog(parent, id);
				editVehicleDl.setVisible(true);

				SelectVehicleTPDialog.this.dispose();
			}
		});

		contentPane.add(btnSelect, "flowx,cell 1 2");

		JButton btnCancel = new JButton("Cancel");

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectVehicleTPDialog.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 2");
	}
}
