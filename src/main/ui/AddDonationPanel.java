package ui;

import javax.swing.JLabel;

public class AddDonationPanel extends MenuOptionPanel {

    public AddDonationPanel(DonationUI app) {
        super(app);

        JLabel title = new JLabel("Add a donation");
        add(title);
    }
}
