package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Donation;

/*
 * The panel where a donation can be added to the donation log
 */
@ExcludeFromJacocoGeneratedReport
public class AddDonationPanel extends MenuOptionPanel {

    /*
     * EFFECTS: creates the add donation panel for the given UI
     */
    public AddDonationPanel(DonationUI app) {
        super(app);
        add(new JLabel("Enter the name of the item: "));
        getDonation(app);
    }

    /*
     * EFFECTS: gets donation name and quantity from the user to add to donation log
     */
    public void getDonation(DonationUI app) {
        JTextField nameField = new JTextField(20);
        add(nameField);

        add(new JLabel("Enter quantity available: "));

        JTextField quantityField = new JTextField(20);
        add(quantityField);

        JButton addDonationButton = new JButton("Add");
        add(addDonationButton);

        addDonationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = nameField.getText().trim();
                int quantity = Integer.parseInt(quantityField.getText().trim());

                if (name.isEmpty()) {
                    displayInvalidNameMessage(app);
                } else if (quantity <= 0) {
                    displayInvalidQuantityMessage(app);
                    Donation donation = new Donation(name, quantity);
                    app.getDonationLog().addDonation(donation);
                    displayDonationAddedMessage(app);
                    app.notifyDonationAdded();
                }
            }
        });
    }
}
