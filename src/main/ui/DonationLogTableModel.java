package ui;

import javax.swing.table.AbstractTableModel;

import model.Donation;
import model.DonationLog;

public class DonationLogTableModel extends AbstractTableModel {
    private DonationLog donationLog;
    private String[] columnNames = {"Donation", "Quantity", "Status"};
    
    public DonationLogTableModel(DonationLog donationLog) {
        this.donationLog = donationLog;
    }

    @Override
    public int getRowCount() {
        return donationLog.getNumEntries();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Donation donation = donationLog.getDonation(rowIndex);
        if (columnIndex == 0) {
            return donation.getName();
        } else if (columnIndex == 1) {
            return donation.getQuantity();
        } else {
            return donation.getStatus();
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

}
