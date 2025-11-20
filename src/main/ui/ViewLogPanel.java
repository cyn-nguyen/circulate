package ui;

import javax.swing.JLabel;

public class ViewLogPanel extends MenuOptionPanel {

    public ViewLogPanel(DonationUI app) {
        super(app);

        JLabel title = new JLabel("View donation log");
        add(title);
    }
}
