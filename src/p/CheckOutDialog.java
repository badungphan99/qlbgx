package p;

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
// phần này để hiện thị ra lỗi khi nhập card id và biển số hoặc là hiển thị giá tiền cần trả
public class CheckOutDialog extends JDialog{
		private JPanel contentPane;
		private JTextField txtcardid, txtlicenseplate;
		private WorkFrame boss;
		private JLabel lblMessage;
		private InforB inforB = new InforB();

		public CheckOutDialog(WorkFrame bossframe) {
			super(bossframe, "Check Out", true);
			setAlwaysOnTop(true);
		
			this.setBounds(440, 200, 600, 200);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
			
			JLabel lblcardid = new JLabel("Card ID");
			contentPane.add(lblcardid, "cell 0 0,alignx trailing");
			
			txtcardid = new JTextField();
			contentPane.add(txtcardid, "cell 1 0,growx");
			
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
                    String mess = null;
					try {
						mess = inforB.checkOut(Integer.parseInt(txtcardid.getText()),txtlicenseplate.getText());
					} catch (Exception e) {
						e.printStackTrace();
					}
					CheckOutDialog.this.setVisible(false);
					JOptionPane.showMessageDialog(bossframe, mess, "Information Check Out",
							JOptionPane.INFORMATION_MESSAGE);
					CheckOutDialog.this.dispose();
				}
			});
			
			btnCancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					CheckOutDialog.this.dispose();
				}
			});
			try {
				BufferedImage images = ImageIO.read(new File("image/icon.jpg"));
				ImageIcon icons = new ImageIcon(images.getScaledInstance(20,20,images.SCALE_SMOOTH));
				btnCheckOut.setIcon(icons);
				btnCancel.setIcon(icons);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
