package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.DonationLog;

/*
 * The panel that displays a filtered donation log according
 * to the item status as selected by the user
 */
@ExcludeFromJacocoGeneratedReport
public class FilterLogPanel extends MenuOptionPanel {
    private DonationLogTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;

    /*
     * EFFECTS: creates a panel that displays the donation log in the given UI,
     *          filtered by whichever item status the user chooses
     */
    public FilterLogPanel(DonationUI app) {
        super(app);

        add(new JLabel("What status would you like to filter the donation log by?"));

        add(new JLabel("\"available\" or \"pending pick up\" or \"picked up\""));

        JTextField statusField = new JTextField(20);
        add(statusField);

        JButton filterButton = new JButton("Filter by this status");
        add(filterButton);
        
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String status = statusField.getText().trim();
                filterLog(app, status);
            }
        });
    }

    /*
     * EFFECTS: filters the donation log by status
     */
    public void filterLog(DonationUI app, String status) {
        if (checkValidStatus(status)) {
            DonationLog unfilteredLog = app.getDonationLog();
            DonationLog filteredLog = unfilteredLog.filterByStatus(status);
            if (scrollPane != null) {
                remove(scrollPane);
            }
            tableModel = new DonationLogTableModel(filteredLog);
            table = new JTable(tableModel);
            scrollPane = new JScrollPane(table);
            add(scrollPane);
            JOptionPane.showMessageDialog(app, "Now viewing filtered donation log", 
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
            revalidate();
            repaint();
        } else {
            displayInvalidStatusMessage();
        }
    }
}
