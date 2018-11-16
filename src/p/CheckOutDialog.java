package p;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class CheckOutDialog extends JDialog{
		private JPanel contentPane;
		private JTextField txtcardid, txttype, txtlicenseplate;
		private WorkFrame boss;
		private JLabel lblMessage;
		private static CheckInDialog checkIn;

		public static CheckInDialog getCheckInFrame() {
			return checkIn;
		}

		public CheckOutDialog(WorkFrame bossframe) {
			super(bossframe, "Check Out", true);
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
			
			JLabel lbllicense = new JLabel("License Plate");
			contentPane.add(lbllicense, "cell 0 1,alignx trailing");
			
			txtlicenseplate = new JTextField();
			contentPane.add(txtlicenseplate, "cell 1 1,growx");
			
			
			JButton btnCheckOut = new JButton("Check Out");
			
			
			lblMessage = new JLabel("Please enter your card_id and license plate");
			contentPane.add(lblMessage, "cell 1 2");
			contentPane.add(btnCheckOut, "flowx,cell 1 3");
			
			JButton btnCancel = new JButton("Cancel");
			contentPane.add(btnCancel, "cell 1 3");

			btnCheckOut.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					CheckOutDialog.this.setVisible(false);
				}
			});
			
			btnCancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					CheckOutDialog.this.setVisible(false);
				}
			});
		}
}
