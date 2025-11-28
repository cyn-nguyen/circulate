package model;

import org.json.JSONObject;

import persistence.Writable;

/*
 * Represents a donation entry with an item name, quantity available, 
 * and status of the item (available, pending pick up, or picked up)
 */
public class Donation implements Writable {
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
        EventLog.getInstance().logEvent(new Event("Donation item \"" + name 
                                                    + "\" status changed to \"" + AVAILABLE + "\""));
    }

    /*
     * MODIFIES: this
     * EFFECTS: marks the donation status as pending pick up
     */
    public void markAsPending() {
        this.status = PENDING;
        EventLog.getInstance().logEvent(new Event("Donation item \"" + name 
                                                    + "\" status changed to \"" + PENDING + "\""));
    }

    /*
     * MODIFIES: this
     * EFFECTS: marks the donation status as picked up
     */
    public void markAsPickedUp() {
        this.status = PICKED_UP;
        EventLog.getInstance().logEvent(new Event("Donation item \"" + name 
                                                    + "\" status changed to \"" + PICKED_UP + "\""));
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
    public void setQuantity(int quantity) {       
        this.quantity = quantity;
    }

    /*
     * REQUIRES: status is "available" or "pending pick up" or "picked up"
     * MODIFIES: this
     * EFFECTS: sets the donation status
     */
    public void setStatus(String status) {
        this.status = status;
        EventLog.getInstance().logEvent(new Event("Donation item \"" + name 
                                                    + "\" status changed to \"" + status + "\""));
    }

    /*
     * EFFECTS: returns this as a JSONObject
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("quantity", this.quantity);
        json.put("status", this.status);
        return json;
    }
}
