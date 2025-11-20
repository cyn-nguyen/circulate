package ui;

import javax.swing.JLabel;

public class FilterLogPanel extends MenuOptionPanel {

    public FilterLogPanel(DonationUI app) {
        super(app);

        JLabel title = new JLabel("Filter the donation log by item status");
        add(title);
    }
}
