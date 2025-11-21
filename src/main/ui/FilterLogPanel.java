package ui;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

public class FilterLogPanel extends ViewLogPanel {

    public FilterLogPanel(DonationUI app) {
        super(app);

        JLabel title = new JLabel("Filter the donation log by item status");
        add(title);

        DonationLogTableModel tableModel = super.getTableModel();
        TableRowSorter<DonationLogTableModel> sorter = new TableRowSorter<>(tableModel);

        JTable table = super.getTable();
        table.setRowSorter(sorter);
    }
}
