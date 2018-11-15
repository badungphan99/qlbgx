package p;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import b.UserB;
import b.InforB;
import b.ParkingB;
import b.ParkingCardB;

public class WorkFrame extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btncheckIn, btncheckOut;
	private JButton btnParking, btnParking_Card, btnInfor;
	private DefaultTableModel model;
	private JMenu mnuser, mnparking, mnparkingcard, mninfor;
	private UserB user;
	private ParkingB parking;
	private ParkingCardB parkingcard;
	private InforB infor;
	private static WorkFrame boss;

	private void initModelUser() throws SQLException {
		model = user.getAllUser();
		table.setModel(model);
	}

	private void initModelParking() throws SQLException {
		model = parking.getAllParking();
		table.setModel(model);
	}

	private void initModelParkingCard() throws SQLException {
		System.out.println("parking card");
		model = parkingcard.getAllParkingCard();
		table.setModel(model);
	}

	private void initModelInfor() throws SQLException {
		System.out.println("em o day xep oi");
		model = infor.getAllInfor();
		table.setModel(model);
	}

	public WorkFrame(FirstFrame firstframe) {
		user = new UserB();
		parking = new ParkingB();
		parkingcard = new ParkingCardB();
		infor = new InforB();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 600);

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

		JMenuItem displayParkingCard = new JMenuItem("Display Parking Cards");
		mnparkingcard.add(displayParkingCard);

		JMenuItem displayInfor = new JMenuItem("Display Infors");
		mninfor.add(displayInfor);

		btncheckIn = new JButton("Check In");
		panel.add(btncheckIn);

		btncheckOut = new JButton("Check Out");
		panel.add(btncheckOut);

		btncheckIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CheckInDialog checkinDl = new CheckInDialog(WorkFrame.this);
				checkinDl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				checkinDl.setVisible(true);

			}
		});

		btncheckOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CheckOutDialog checkoutDl = new CheckOutDialog(WorkFrame.this);
				checkoutDl.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				checkoutDl.setVisible(true);

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
				// TODO Auto-generated method stub
				try {
					initModelUser();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		displayParking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					initModelParking();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	}

}
