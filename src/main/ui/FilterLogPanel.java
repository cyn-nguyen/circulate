package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.DonationLog;

public class FilterLogPanel extends MenuOptionPanel {
    private DonationLogTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;

    public FilterLogPanel(DonationUI app) {
        super(app);

        JLabel statusLabel = new JLabel("What status would you like to filter the donation log by?");
        add(statusLabel);

        JLabel optionsLabel = new JLabel("\"available\" or \"pending pick up\" or \"picked up\"");
        add(optionsLabel);

        JTextField statusField = new JTextField(20);
        add(statusField);

        JButton filterButton = new JButton("Filter by this status");
        add(filterButton);
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String status = statusField.getText().trim();
                if (status.equals("available") || status.equals("pending pick up") || status.equals("picked up")) {
                    DonationLog unfilteredLog = app.getDonationLog();
                    DonationLog filteredLog = unfilteredLog.filterByStatus(status);
                    if (scrollPane != null) {
                        remove(scrollPane);
                    }
                    tableModel = new DonationLogTableModel(filteredLog);
                    table = new JTable(tableModel);
                    scrollPane = new JScrollPane(table);
                    add(scrollPane);
                    JOptionPane.showMessageDialog(app, "Now viewing filtered donation log", "Success", JOptionPane.INFORMATION_MESSAGE);
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(app, "Please enter valid status", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            };
        });

    }
}
