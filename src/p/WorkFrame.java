
package p;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import b.UserB;
import b.InforB;
import b.ParkingB;
import b.ParkingCardB;

public class WorkFrame extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JButton btncheckIn, btncheckOut;
	private JButton btnParking, btnParking_Card, btnInfor;
	private DefaultTableModel model;
	private JMenu mnuser, mnparking, mnparkingcard, mninfor;
	private JMenuItem addParking, editParking, deleteParking;
	private JPanel jpanel;
	private JLabel label;

	private UserB user;
	private ParkingB parking;
	private ParkingCardB parkingcard;
	private InforB infor;
	private static WorkFrame boss;

	private void initModelUser(){
		model = user.getAllUser();
		table.setModel(model);
	}

	private void initModelParking(){
		model = parking.getAllParking();
		table.setModel(model);
	}

	private void initModelParkingCard() throws SQLException {
		model = parkingcard.getAllParkingCard();
		table.setModel(model);
	}

	private void initModelInfor() throws SQLException {
		model = infor.getAllInfor();
		table.setModel(model);
	}

	public WorkFrame(FirstFrame firstframe) {
		user = new UserB();
		parking = new ParkingB();
		parkingcard = new ParkingCardB();
		infor = new InforB();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAcc = new JMenu("Account");
		menuBar.add(mnAcc);

		JMenuItem mntmChange = new JMenuItem("Change");
		mnAcc.add(mntmChange);

		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mnAcc.add(mntmLogOut);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		mnuser = new JMenu("User");
		mnparking = new JMenu("Parking");
		mnparkingcard = new JMenu("Parking Card");
		mninfor = new JMenu("Infor");

		menuBar.add(mnuser);
		menuBar.add(mnparking);
		menuBar.add(mnparkingcard);
		menuBar.add(mninfor);

		mnuser.setEnabled(false);

		JMenuItem displayUser = new JMenuItem("Display Users");
		mnuser.add(displayUser);

		JMenuItem addUser = new JMenuItem("Add User");
		mnuser.add(addUser);

		JMenuItem editUser = new JMenuItem("Edit User");
		mnuser.add(editUser);

		JMenuItem deleteUser = new JMenuItem("Delete User");
		mnuser.add(deleteUser);

		JMenuItem displayParking = new JMenuItem("Display Parkings");
		mnparking.add(displayParking);

		addParking = new JMenuItem("Add Parking");
		mnparking.add(addParking);

		addParking.setEnabled(false);

		editParking = new JMenuItem("Edit Parking");
		mnparking.add(editParking);

		editParking.setEnabled(false);

		deleteParking = new JMenuItem("Delete Parking");
		mnparking.add(deleteParking);

		deleteParking.setEnabled(false);

		JMenuItem displayParkingCard = new JMenuItem("Display Parking Cards");
		mnparkingcard.add(displayParkingCard);

		JMenuItem displayInfor = new JMenuItem("Display Infors");
		mninfor.add(displayInfor);
		btncheckIn = new JButton("Check In");
		panel.add(btncheckIn);

		btncheckOut = new JButton("Check Out");
		panel.add(btncheckOut);
		jpanel = new JPanel();
//		contentPane.add(jpanel,BorderLayout.CENTER);
		label = new JLabel();
		// label.setSize(1000,300);
		label.setSize(1000, 300);
		/*
		 * try { BufferedImage image = ImageIO.read(new File("image/baixe3.jpg"));
		 * ImageIcon icon = new
		 * ImageIcon(image.getScaledInstance(1490,720,image.SCALE_SMOOTH));
		 * label.setIcon(icon); } catch (IOException e) { e.printStackTrace(); }
		 * jpanel.add(label);
		 */
		btncheckIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CheckInDialog checkinDl = new CheckInDialog(WorkFrame.this);
				checkinDl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				checkinDl.setVisible(true);
				try {
					initModelInfor();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btncheckOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CheckOutDialog checkoutDl = new CheckOutDialog(WorkFrame.this);
				checkoutDl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				checkoutDl.setVisible(true);
				try {
					initModelInfor();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		mntmLogOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				WorkFrame.this.setVisible(false);
				firstframe.setVisible(true);

			}
		});
		displayUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initModelUser();
			}
		});

		addUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddUserDialog addUserDl = new AddUserDialog(WorkFrame.this);
				addUserDl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				addUserDl.setVisible(true);
				
				initModelUser();
			}
		});

		editUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectUserEditDialog slUserEditDl = new SelectUserEditDialog(WorkFrame.this);
				slUserEditDl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				slUserEditDl.setVisible(true);
				
				initModelUser();
			}
		});

		deleteUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteUserDialog delUserDl = new DeleteUserDialog(WorkFrame.this);
				delUserDl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				delUserDl.setVisible(true);
				
				initModelUser();
			}
		});
		
		displayParking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initModelParking();
			}
		});

		addParking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddParkingDialog addParkingDl = new AddParkingDialog(WorkFrame.this);
				addParkingDl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				addParkingDl.setVisible(true);

				initModelParking();
			}
		});

		displayParkingCard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					initModelParkingCard();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		displayInfor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					initModelInfor();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	void enableControl() {
		mnuser.setEnabled(true);
		addParking.setEnabled(true);
		editParking.setEnabled(true);
		deleteParking.setEnabled(true);
	}

}