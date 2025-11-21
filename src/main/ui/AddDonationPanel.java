package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Donation;

public class AddDonationPanel extends MenuOptionPanel {
    private AddDonationPanel addDonationPanel;

    public AddDonationPanel(DonationUI app) {
        super(app);

        JLabel title = new JLabel("Add a donation entry and click 'Add'");
        add(title);

        getDonation(app);
    }

    public void getDonation(DonationUI app) {
        JLabel nameLabel = new JLabel("Enter the name of the item: ");
        add(nameLabel);
        
        JTextField nameField = new JTextField(20);
        add(nameField);

        JLabel quantityLabel = new JLabel("Enter quantity available: ");
        add(quantityLabel);

        JTextField quantityField = new JTextField(20);
        add(quantityField);

        JButton addDonationButton = new JButton("Add");
        add(addDonationButton);
        addDonationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = nameField.getText().trim();
                String quantityString = quantityField.getText().trim();
                // JLabel resultLabel = new JLabel("Please enter a valid name");
                // add(resultLabel);
                int quantity = Integer.parseInt(quantityString);
                Donation donation = new Donation(name, quantity);
                int numEntries = app.getDonationLog().getNumEntries();
                app.getDonationLog().addDonation(donation);
                
                int newNumEntries = app.getDonationLog().getNumEntries();
                if (newNumEntries > numEntries) {
                    JOptionPane.showMessageDialog(app, "Donation added!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    app.notifyDonationAdded();
                }
                // while (quantityString.isEmpty()) {
                //     JLabel resultLabel = new JLabel("Please enter a valid name");
                //     add(resultLabel);
                //     String quantityString = quantityField.getText().trim();
                // }
                // try {
                //     int quantity = Integer.parseInt(quantityString);
                //     Donation donation = new Donation(name, quantity);
                //     app.getDonationLog().addDonation(donation);
                // } catch (NumberFormatException e) {

                }
            });
    }
}
