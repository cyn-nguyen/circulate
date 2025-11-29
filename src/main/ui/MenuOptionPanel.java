package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

/*
 * Represents a panel associated to an option from the menu bar
 * that will be added to UI
 */
@ExcludeFromJacocoGeneratedReport
public abstract class MenuOptionPanel extends JPanel {
    private DonationUI app;
    
    /*
     * EFFECTS: creates a menu option panel for the given UI
     */
    public MenuOptionPanel(DonationUI app) {
        this.app = app;
    }

    /*
     * EFFECTS: displays a message to the user if an invalid status is entered
     */
    public void displayInvalidStatusMessage() {
        JOptionPane.showMessageDialog(app, "Please enter valid status", "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    /*
     * EFFECTS: displays a message to the user if an invalid name is entered
     */
    public void displayInvalidNameMessage() {
        JOptionPane.showMessageDialog(app, "Please enter a valid name", "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    /*
     * EFFECTS: displays a message to the user if an invalid quantity is entered
     */
    public void displayInvalidQuantityMessage() {
        JOptionPane.showMessageDialog(app, "Please enter a valid quantity", "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    /*
     * EFFECTS: displays a message to the user if the donation was added successfully
     */
    public void displayDonationAddedMessage() {
        JOptionPane.showMessageDialog(app, "Donation added! View donation log for changes", 
                                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /*
     * EFFECTS: displays a message to the user if the donation was added successfully
     */
    public void displayNoSuchDonationMessage(String name) {
        JOptionPane.showMessageDialog(app, "Item \'" + name + "\' not in donation log", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
    }

    /*
     * EFFECTS: returns whether or not the user's selected status is valid
     */
    public boolean checkValidStatus(String newStatus) {
        return (newStatus.equals("available") || newStatus.equals("pending pick up")
                || newStatus.equals("picked up"));
    }
}
