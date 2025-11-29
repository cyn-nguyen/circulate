package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Donation;
import model.DonationLog;

/*
 * The panel where a donation's status can be changed
 */
@ExcludeFromJacocoGeneratedReport
public class ChangeStatusPanel extends MenuOptionPanel {

    /*
     * EFFECTS: creates the change status panel for the given UI
     */
    public ChangeStatusPanel(DonationUI app) {
        super(app);
        add(new JLabel("Enter the name of the item: "));
        changeStatus(app);
    }

    /*
     * EFFECTS: changes the status of a donation according to user's input
     */
    public void changeStatus(DonationUI app) {
        JTextField nameField = new JTextField(20);
        add(nameField);

        add(new JLabel("What status would you like to change this item to: "));

        add(new JLabel("\"available\" or \"pending pick up\" or \"picked up\""));

        JTextField changeField = new JTextField(20);
        add(changeField);

        JButton changeStatusButton = new JButton("Change item status");
        add(changeStatusButton);

        changeStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = nameField.getText().trim();
                DonationLog donationLog = app.getDonationLog();
                String newStatus = changeField.getText().trim();

                if (!donationLog.hasDonation(name)) {
                    displayNoSuchDonationMessage(name);
                } else if (checkValidStatus(newStatus)) {
                    setNewStatus(app, donationLog.getDonation(name), newStatus);
                } else {
                    displayInvalidStatusMessage();
                }
            }
        });
    }

    /*
     * EFFECTS: changes the given donation's status to the new status in the given UI
     */
    private void setNewStatus(DonationUI app, Donation donation, String newStatus) {
        donation.setStatus(newStatus);
        JOptionPane.showMessageDialog(app, "Donation status changed! View donation log for changes",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
        app.notifyDonationAdded();
    }
}
