package model;

/*
 * Represents a donation entry with an item name, quantity available, 
 * and status of the item (available, pending pick up, or picked up)
 */
public class Donation {
    private static final String AVAILABLE = "available";
    private static final String PENDING = "pending pick up";
    private static final String PICKED_UP = "picked up";

    private String name;
    private int quantity; 
    private String status;

    /*
     * REQUIRES: name has a non-zero length and quantity > 0
     * EFFECTS: constructs a new Donation item with the given name 
     *          and quantity; item's status is available
     */
    
    public Donation(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.status = AVAILABLE;
        }

    /*
     * MODIFIES: this
     * EFFECTS: marks the donation status as available
     */
    public void markAsAvailable() {
        this.status = AVAILABLE;
        }
    
    /*
     * MODIFIES: this
     * EFFECTS: marks the donation status as pending pick up
     */
    public void markAsPending() {
        this.status = PENDING;
        }

    /*
     * MODIFIES: this
     * EFFECTS: marks the donation status as picked up
     */
    public void markAsPickedUp() {
        this.status = PICKED_UP;
        }

    // getters
    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getStatus() {
        return this.status;
    }

    // setters
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
