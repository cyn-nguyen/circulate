package ui;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.DonationLog;

/*
 * The panel that displays the entire donation log
 */
@ExcludeFromJacocoGeneratedReport
public class ViewLogPanel extends MenuOptionPanel {
    private DonationLog donationLog;
    private DonationLogTableModel tableModel;
    private JTable table;

    /*
     * EFFECTS: creates a panel that displays the donation log in the given UI
     */
    public ViewLogPanel(DonationUI app) {
        super(app);

        donationLog = app.getDonationLog();

        tableModel = new DonationLogTableModel(donationLog);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates the table every time a donation is added/changed
     */
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
