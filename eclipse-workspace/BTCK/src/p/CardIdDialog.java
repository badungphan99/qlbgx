package p;
// class này để hiện thị số vé xe sau khi check in (Gióng như việc in ra vé xe  )

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardIdDialog extends JDialog {
    private JPanel contentPane;

    public CardIdDialog(WorkFrame bossframe,int card_id) {
        super(bossframe, "Check In", true);
        setAlwaysOnTop(true);
        this.setSize(600, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][]"));

        JLabel card_number = new JLabel("Card number: ");
        contentPane.add(card_number, "cell 0 1,alignx trailing");

        JLabel numberOfCard = new JLabel(String.valueOf(card_id));
        contentPane.add(numberOfCard, "cell 1 1,growx");


        JButton btnOk = new JButton("Ok");
        contentPane.add(btnOk, "flowx,cell 1 4");

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardIdDialog.this.setVisible(false);
            }
        });

    }
}
