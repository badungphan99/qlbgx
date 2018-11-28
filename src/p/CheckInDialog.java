package p;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import b.InforB;
import b.ParkingB;
import b.Validation;
import e.Infor;
import net.miginfocom.swing.MigLayout;

public class CheckInDialog extends JDialog {
	private JPanel contentPane;
	private JTextField txtlicenseplate;
	private WorkFrame boss;
	private JLabel lblMessage;
	private static CheckInDialog checkIn;
	private InforB inforB = new InforB();
	private JComboBox<String> vehicleTypeBox;

	public CheckInDialog(WorkFrame bossframe) {
		super(bossframe, "CheckIn", true);
		setAlwaysOnTop(true);

		this.setBounds(440, 200, 600, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));

		JLabel lbltype = new JLabel("Vehicle Type");
		contentPane.add(lbltype, "cell 0 0,alignx trailing");

		ParkingB parkingB = new ParkingB();
		String[] vehicleType = parkingB.getParkingLot();
		vehicleTypeBox = new JComboBox<String>(vehicleType);
		contentPane.add(vehicleTypeBox, "cell 1 0,growx");

		JLabel lbllicense = new JLabel("License Plate");
		contentPane.add(lbllicense, "cell 0 1,alignx trailing");

		txtlicenseplate = new JTextField();
		contentPane.add(txtlicenseplate, "cell 1 1,growx");

		JButton btnAdd = new JButton("Check In");
		Validation validation = new Validation();
		lblMessage = new JLabel("Please enter vehicle type and license plate");
		contentPane.add(lblMessage, "cell 1 3");
		contentPane.add(btnAdd, "flowx,cell 1 4");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				String vehicle_type = vehicleTypeBox.getSelectedItem().toString();
				int id_vehicle = 0;
				if (vehicle_type.equalsIgnoreCase("Bicycle")) {
					id_vehicle = 1;
				} else if (vehicle_type.equalsIgnoreCase("Motorbike")) {
					id_vehicle = 2;
				} else {
					id_vehicle = 3;
				}

				if (validation.isValidateLicense(txtlicenseplate.getText())) {
					int code = inforB.checkin(id_vehicle, txtlicenseplate.getText());
					if (code == -1) {
						JOptionPane.showMessageDialog(CheckInDialog.this, "Please switch to another parking",
								"Check in", JOptionPane.INFORMATION_MESSAGE);
					} else {
						String mess = "Your card id: " + String.valueOf(code);
						JOptionPane.showMessageDialog(CheckInDialog.this, mess, "Edit User",
								JOptionPane.INFORMATION_MESSAGE);
					}
					CheckInDialog.this.dispose();
				} else {
					lblMessage.setForeground(Color.RED);
					lblMessage.setText("License plate is not validate!");
				}
				/*
				 * code: bình thường sẽ trả về số id cua vé nhưng nếu trả về -1 tức là bãi xe đã
				 * hết chỗ trả về -2 tức là có lỗi lúc insert nhưng không cần quan tâm đâu chỉ
				 * bắn ra log lỗi hệ thống thôi không hiện thị ra cho người dùng
				 */

				

			}
		});

		JButton btnCancel = new JButton("Cancel");
		contentPane.add(btnCancel, "cell 1 4");

		try {
			BufferedImage images = ImageIO.read(new File("image/icon.jpg"));
			ImageIcon icons = new ImageIcon(images.getScaledInstance(20, 20, images.SCALE_SMOOTH));
			btnAdd.setIcon(icons);
			btnCancel.setIcon(icons);
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CheckInDialog.this.dispose();

			}
		});
	}
}
