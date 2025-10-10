package model;

import java.util.ArrayList;

/*
 * Represents a donation log holding Donation items in the order
 * they are added
 */
public class DonationLog {
    private ArrayList<Donation> donationLog;

    /*
     * EFFECTS: constructs a new empty DonationLog item
     */
    public DonationLog() {
        this.donationLog = new ArrayList<>();
    }
    
    /*
     * MODIFIES: this
     * EFFECTS: adds a donation to the end of the donation log
     */
    public void addDonation(Donation donation) {
        this.donationLog.add(donation);
    }

    /*
     * REQUIRES: status is either "available" or "pending pick up" or "picked up"
     * EFFECTS: returns a new donation log that is a subset of the parent donation
     *          log containing only donation entries with the same status
     */
    public DonationLog filterByStatus(String status) {
        DonationLog filteredDonationLog = new DonationLog();
        for (Donation donation : this.donationLog) {
            if (donation.getStatus().equals(status)) {
                filteredDonationLog.addDonation(donation);
            }
        }
        return filteredDonationLog;
    } 

    /*
     * EFFECTS: checks if a donation with donationName exists in the donation Log;
     *          returns true if so, otherwise returns false
     */
    public boolean hasDonation(String donationName) {
        for (Donation donation : this.donationLog) {
            if (donation.getName().equals(donationName)) {
                return true;
            }
        }
        return false;
    }

    /*
     * REQUIRES: donation log hasDonation()
     * EFFECTS: returns the donation entry corresponding to the given donationName
     */
    public Donation getDonation(String donationName) {
        for (Donation donation : this.donationLog) {
            if (donation.getName().equals(donationName)) {
                return donation;
            }
        }
        return null;
    }

     /*
     * REQUIRES: getNumEntries() > 0 and index >= 0
     * EFFECTS: returns the donation entry corresponding to the given index
     */
    public Donation getDonation(int index) {
        return this.donationLog.get(index);
    }

    /*
     * REQUIRES: getNumEntries() > 0
     * EFFECTS: returns true if all donations in the donation log have the same
     *          status, otherwise returns false
     */
    public boolean isFiltered() {
        boolean sameStatus = true;
        String firstDonationInLogStatus = this.getDonation(0).getStatus();

        for (Donation donation : this.donationLog) {
            if (!donation.getStatus().equals(firstDonationInLogStatus)) {
                sameStatus = false;
                break;
            }
        }
        return sameStatus;
    }

    // getters
    public int getNumEntries() {
        return this.donationLog.size();
    }

}
