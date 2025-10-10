package model;

/*
 * Represents a donation log holding Donation items in the order
 * they are added
 */
public class DonationLog {

    /*
     * EFFECTS: constructs a new empty DonationLog item
     */
    public DonationLog() {
        // stub
    }
    
    /*
     * MODIFIES: this
     * EFFECTS: adds a donation to the end of the donation log
     */
    public void addDonation(Donation donation) {
        // stub
    }

    /*
     * REQUIRES: status is either "available" or "pending pick up" or "picked up"
     * EFFECTS: returns a new donation log that is a subset of the parent donation
     *          log containing only donation entries with the same status
     */
    public DonationLog filterByStatus(String status) {
        return null; // stub
    } 

    /*
     * EFFECTS: checks if a donation with donationName exists in the donation Log;
     *          returns true if so, otherwise returns false
     */
    public boolean hasDonation(String donationName) {
        return false;
    }

    /*
     * REQUIRES: donation log hasDonation()
     * EFFECTS: returns the donation entry corresponding to the given donationName
     */
    public Donation getDonation(String donationName) {
        return null;
    }

    // getters
    public int getNumEntries() {
        return -1; // stub
    }
}
