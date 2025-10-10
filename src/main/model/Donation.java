package model;

/*
 * Represents a donation entry with an item name, quantity available, 
 * and status of the item (available, pending pick up, or picked up)
 */
public class Donation {

    /*
     * REQUIRES: name has a non-zero length and quantity > 0
     * EFFECTS: constructs a new Donation item with the given name 
     *          and quantity; item's status is available
     */
    public Donation(String name, int quantity) {
        // stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: marks the donation status as available
     */
    public void markAsAvailable() {
        // stub
    }
    
    /*
     * MODIFIES: this
     * EFFECTS: marks the donation status as pending pick up
     */
    public void markAsPending() {
        // stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: marks the donation status as picked up
     */
    public void markAsPickedUp() {
        // stub
    }

    // getters
    public String getName() {
        return ""; // stub
    }

    public int getQuantity() {
        return -1; // stub
    }

    public String getStatus() {
        return ""; // stub
    }

    // setters
    public void setQuantity(int quantity) {
        // stub
    }
}
