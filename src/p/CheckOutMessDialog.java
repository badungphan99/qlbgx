package p;

import b.InforB;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckOutMessDialog extends JDialog{
    private JPanel contentPane;
    private JTextField txtcardid, txttype, txtlicenseplate;
    private WorkFrame boss;
    private JLabel lblMessage;
    private static CheckInDialog checkIn;
    private InforB inforB = new InforB();

    public CheckOutMessDialog(WorkFrame bossframe, String mess) {
        super(bossframe, "Check Out", true);
        setAlwaysOnTop(true);
        this.setSize(170, 130);

        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
       // contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
        //System.out.println("ahihi");
        contentPane.setLayout(new BorderLayout());
//        JLabel card_number = new JLabel("Card number: ");
//        contentPane.add(card_number, "cell 0 1,alignx trailing");
//
//        JLabel numberOfCard = new JLabel(String.valueOf(card_id));
//        contentPane.add(numberOfCard, "cell 1 1,growx");

        JLabel card_number = new JLabel(mess);

        //contentPane.add(card_number, "cell 2 1,alignx trailing");
        contentPane.add(card_number,BorderLayout.CENTER);

        contentPane.add(card_number);
        JButton btnOk = new JButton("Ok");
       // contentPane.add(btnOk, "flowx,cell 0 2");
        contentPane.add(btnOk,BorderLayout.PAGE_END);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CheckOutMessDialog.this.setVisible(false);
            }
        });

    }
}
