package ui;

import javax.swing.JLabel;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

/*
 * Home page panel
 */
@ExcludeFromJacocoGeneratedReport
public class HomePanel extends MenuOptionPanel {

    /*
     * EFFECTS: creates the home page panel for the given UI
     */
    public HomePanel(DonationUI app) {
        super(app);

        JLabel welcomeLabel1 = new JLabel("Welcome to circulate: a donation tracking application");
        add(welcomeLabel1);
        JLabel welcomeLabel2 = new JLabel("Please select an option from the menu bar");
        add(welcomeLabel2);
    }
}
