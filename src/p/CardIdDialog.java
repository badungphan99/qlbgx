package p;
// class này để hiện thị số vé xe sau khi check in (Gióng như việc in ra vé xe  )

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardIdDialog extends JDialog {
    private JPanel contentPane;

    public CardIdDialog(WorkFrame bossframe,int card_id) {
        super(bossframe, "Check In", true);
        setAlwaysOnTop(true);
        this.setSize(170, 130);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
       // contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
        contentPane.setLayout(new BorderLayout());
        JLabel card_number = new JLabel("Card number: ");
       // contentPane.add(card_number, "cell 1 1,alignx trailing");
        contentPane.add(card_number,BorderLayout.LINE_START);
        JLabel numberOfCard = new JLabel(String.valueOf(card_id));
        //contentPane.add(numberOfCard, "cell 2 1,growx");
        contentPane.add(numberOfCard, BorderLayout.CENTER);
/*

        String card_number = "Card_number";
        String numberOfCard = String.valueOf(card_id);
        String cardAndnumber = card_number + numberOfCard;
*/

        JButton btnOk = new JButton("Ok");
        contentPane.add(btnOk, BorderLayout.PAGE_END);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardIdDialog.this.setVisible(false);
            }
        });

    }
}
