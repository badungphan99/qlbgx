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
import javax.swing.table.DefaultTableModel;
import b.UserB;
import b.InforB;
import b.ParkingB;
import b.ParkingCardB;
import java.awt.Color;

public class WorkFrame extends JFrame {
	//private static final Object CEEB = ;
	private JPanel contentPane;
	private JTable table;
	private JButton btncheckIn, btncheckOut;
	private JButton btnParking, btnParking_Card, btnInfor;
	private DefaultTableModel model;
	private JMenu mnUser, mnParking, mnparkingcard, mnInfor;
	private JMenu mnDisplayUser, mnDisplayParking;
	private JMenuItem addParking, editParking, activeParking, deactiveParking;
	private JPanel jpanel;
	private JLabel label;
	private UserB user;
	private ParkingB parking;
	private ParkingCardB parkingcard;
	private InforB infor;
	private static WorkFrame boss;

	private void initModelUser() {
		model = user.getAllUser();
		table.setModel(model);
	}

	private void initModelUserActive(boolean active) {
		model = user.getAllUserActive(active);
		table.setModel(model);
	}

	private void initModelParking() {
		model = parking.getAllParking();
		table.setModel(model);
	}

	private void initModelParkingActive(boolean active) {
		model = parking.getAllParkingActive(active);
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
		contentPane.setBackground(Color.decode("#87CEEB"));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setBackground(Color.decode("#87CEEB"));
		mnUser = new JMenu("User");
		mnParking = new JMenu("Parking");
		mnparkingcard = new JMenu("Parking Card");
		mnInfor = new JMenu("Infor");

		menuBar.add(mnUser);
		menuBar.add(mnParking);
		menuBar.add(mnparkingcard);
		menuBar.add(mnInfor);

		mnUser.setEnabled(false);

		mnDisplayUser = new JMenu("Display");
		mnUser.add(mnDisplayUser);
		mnUser.addSeparator();
		
		JMenuItem displayAllUser = new JMenuItem("All");
		mnDisplayUser.add(displayAllUser);

		JMenuItem displayActiveUser = new JMenuItem("Active");
		mnDisplayUser.add(displayActiveUser);

		JMenuItem displayNotActiveUser = new JMenuItem("Not Active");
		mnDisplayUser.add(displayNotActiveUser);

		JMenuItem addUser = new JMenuItem("Add User");
		mnUser.add(addUser);

		JMenuItem editUser = new JMenuItem("Edit User");
		mnUser.add(editUser);

		JMenuItem activeUser = new JMenuItem("Active User");
		mnUser.add(activeUser);

		JMenuItem deactiveUser = new JMenuItem("Deactive User");
		mnUser.add(deactiveUser);

		mnDisplayParking = new JMenu("Display");
		mnParking.add(mnDisplayParking);
		mnParking.addSeparator();
		
		JMenuItem displayAllParking = new JMenuItem("All");
		mnDisplayParking.add(displayAllParking);

		JMenuItem displayActiveParking = new JMenuItem("Active");
		mnDisplayParking.add(displayActiveParking);

		JMenuItem displayNotActiveParking = new JMenuItem("Not Active");
		mnDisplayParking.add(displayNotActiveParking);
		
		addParking = new JMenuItem("Add Parking");
		mnParking.add(addParking);

		addParking.setEnabled(false);

		editParking = new JMenuItem("Edit Parking");
		mnParking.add(editParking);

		editParking.setEnabled(false);

		activeParking = new JMenuItem("Active Parking");
		mnParking.add(activeParking);

		activeParking.setEnabled(false);

		deactiveParking = new JMenuItem("Deactive Parking");
		mnParking.add(deactiveParking);

		deactiveParking.setEnabled(false);

		JMenuItem displayParkingCard = new JMenuItem("Display Parking Cards");
		mnparkingcard.add(displayParkingCard);

		JMenuItem displayInfor = new JMenuItem("Display Infors");
		mnInfor.add(displayInfor);
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
		
		displayAllUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initModelUser();
			}
		});

		displayActiveUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initModelUserActive(true);
			}
		});

		displayNotActiveUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initModelUserActive(false);

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

				initModelUserActive(true);
			}
		});

		activeUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ActiveUserDialog actUserDl = new ActiveUserDialog(WorkFrame.this, "Active", true);
				actUserDl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				actUserDl.setVisible(true);

				initModelUserActive(true);
			}
		});
		deactiveUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActiveUserDialog actUserDl = new ActiveUserDialog(WorkFrame.this, "Deactive", false);
				actUserDl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				actUserDl.setVisible(true);

				initModelUserActive(false);
			}
		});

		displayAllParking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initModelParking();
			}
		});

		displayActiveParking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initModelParkingActive(true);
			}
		});
		
		displayNotActiveParking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initModelParkingActive(false);
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

		editParking.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectParkingEditDialog selectPEditDl = new SelectParkingEditDialog(WorkFrame.this);
				selectPEditDl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				selectPEditDl.setVisible(true);
				
				initModelParkingActive(true);
			}
		});
		
		activeParking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ActiveParkingDialog actParkingDl = new ActiveParkingDialog(WorkFrame.this, "Active", true);
				actParkingDl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				actParkingDl.setVisible(true);

				initModelParkingActive(true);
			}
		});
		deactiveParking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ActiveParkingDialog actParkingDl = new ActiveParkingDialog(WorkFrame.this, "Deactive", false);
				actParkingDl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				actParkingDl.setVisible(true);

				initModelParkingActive(false);
			}
		});

		displayParkingCard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					initModelParkingCard();
				} catch (SQLException e1) {
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
					e1.printStackTrace();
				}
			}
		});

	}

	void enableControl() {
		mnUser.setEnabled(true);
		addParking.setEnabled(true);
		editParking.setEnabled(true);
		activeParking.setEnabled(true);
		deactiveParking.setEnabled(true);
	}

}