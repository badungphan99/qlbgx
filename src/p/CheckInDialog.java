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
import e.Infor;
import net.miginfocom.swing.MigLayout;

public class CheckInDialog extends JDialog {
	private JPanel contentPane;
	private JTextField txtcardid, txttype, txtlicenseplate;
	private WorkFrame boss;
	private JLabel lblMessage;
	private static CheckInDialog checkIn;
	private InforB inforB = new InforB();

	/*public static CheckInDialog getCheckInFrame() {
		return checkIn;
	}*/

	public CheckInDialog(WorkFrame bossframe) {
		super(bossframe, "CheckIn", true);
		setAlwaysOnTop(true);

	
		this.setSize(500, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
		
//		JLabel lblcardid = new JLabel("Card ID");
//		contentPane.add(lblcardid, "cell 0 0,alignx trailing");
		
//		txtcardid = new JTextField();
//		contentPane.add(txtcardid, "cell 1 0,growx");
		
//		txtcardid.setColumns(10);
		
		JLabel lbltype = new JLabel("Vehicle Type");
		contentPane.add(lbltype, "cell 0 1,alignx trailing");
		
		txttype = new JTextField();
		txttype.getSize(null);
		contentPane.add(txttype, "cell 1 1,growx");
		
		JLabel lbllicense = new JLabel("License Plate");
		contentPane.add(lbllicense, "cell 0 2,alignx trailing");
		
		txtlicenseplate = new JTextField();
		contentPane.add(txtlicenseplate, "cell 1 2,growx");
		
		JButton btnAdd = new JButton("Check In");
		
		
		lblMessage = new JLabel("Please enter vehicle type and license plate");
		contentPane.add(lblMessage, "cell 1 3");
		contentPane.add(btnAdd, "flowx,cell 1 4");

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				Infor infor = inforB.checkin(txttype.getText(),txtlicenseplate.getText());
				CheckInDialog.this.setVisible(false);
				/*CardIdDialog cardIdDialog = new CardIdDialog(bossframe, infor.getCardid());
				cardIdDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				cardIdDialog.setVisible(true);*/
				/*JOptionPane.showMessageDialog(CheckInDialog.this, "", "CheckIn",
						JOptionPane.INFORMATION_MESSAGE);*/
			}
		});


		JButton btnCancel = new JButton("Cancel");
		contentPane.add(btnCancel, "cell 1 4");

		try {
			BufferedImage images = ImageIO.read(new File("image/icon.jpg"));
			ImageIcon icons = new ImageIcon(images.getScaledInstance(20,20,images.SCALE_SMOOTH));
			btnAdd.setIcon(icons);
			btnCancel.setIcon(icons);
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckInDialog.this.setVisible(false);
				
			}
		});
	}
}
