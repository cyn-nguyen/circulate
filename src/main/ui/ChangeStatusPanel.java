package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Donation;
import model.DonationLog;

public class ChangeStatusPanel extends MenuOptionPanel {

    public ChangeStatusPanel(DonationUI app) {
        super(app);
        changeStatus(app);
    }

    public void changeStatus(DonationUI app) {
        JLabel nameLabel = new JLabel("Enter the name of the item: ");
        add(nameLabel);
        
        JTextField nameField = new JTextField(20);
        add(nameField);

        JLabel changeLabel = new JLabel("What status would you like to change this item to: ");
        add(changeLabel);

        JLabel optionsLabel = new JLabel("\"available\" or \"pending pick up\" or \"picked up\"");
        add(optionsLabel);

        JTextField changeField = new JTextField(20);
        add(changeField);

        JButton changeStatusButton = new JButton("Change item status");
        add(changeStatusButton);
        changeStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = nameField.getText().trim();
                DonationLog donationLog = app.getDonationLog();
                Donation donation = donationLog.getDonation(name);

                String newStatus = changeField.getText().trim();
                if (!donationLog.hasDonation(name)) {
                    JOptionPane.showMessageDialog(app, "Item \'"+ name +"\' not in donation log", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else if (newStatus.equals("available") || newStatus.equals("pending pick up") || newStatus.equals("picked up")) {
                    donation.setStatus(newStatus);
                    JOptionPane.showMessageDialog(app, "Donation status changed! View donation log for changes", "Success", JOptionPane.INFORMATION_MESSAGE);
                    app.notifyDonationAdded();
                } else {
                    JOptionPane.showMessageDialog(app, "Please enter valid status", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            };
        });
    }
}
