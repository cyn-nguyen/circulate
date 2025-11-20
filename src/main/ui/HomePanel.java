package ui;

import javax.swing.JLabel;

public class HomePanel extends MenuOptionPanel {

    public HomePanel(DonationUI app) {
        super(app);

        JLabel welcomeLabel1 = new JLabel("Welcome to circulate: a donation tracking application");
        add(welcomeLabel1);
        JLabel welcomeLabel2 = new JLabel("Please select an option from the menu bar");
        add(welcomeLabel2);
    }
}
