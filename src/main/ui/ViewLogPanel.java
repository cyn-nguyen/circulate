package ui;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.DonationLog;

public class ViewLogPanel extends MenuOptionPanel {
    private DonationLog donationLog;
    private DonationLogTableModel tableModel;
    private JTable table;

    public ViewLogPanel(DonationUI app) {
        super(app);

        donationLog = app.getDonationLog();

        JLabel numEntries = new JLabel("Total number of donations: " + donationLog.getNumEntries());
        add(numEntries);

        tableModel = new DonationLogTableModel(donationLog);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public void updateTable() {
        tableModel.fireTableDataChanged();
    }

    public DonationLogTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }
}
