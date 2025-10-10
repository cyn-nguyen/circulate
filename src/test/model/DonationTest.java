package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DonationTest {
    private Donation testDonation;

    @BeforeEach
    void runBefore() {
        testDonation = new Donation("test donation", 10);
    }

    /*
     * Test constructor and getters
     */
    @Test
    void testConstructor() {
        assertEquals("test donation", testDonation.getName());
        assertEquals(10, testDonation.getQuantity());
        assertEquals("available", testDonation.getStatus());
    }

    /*
     * Test switching from all statuses to "available"
     */
    @Test
    void testMarkAsAvailable() {
        testDonation.markAsAvailable();
        assertEquals("available", testDonation.getStatus());
        testDonation.markAsPending();
        testDonation.markAsAvailable();
        assertEquals("available", testDonation.getStatus());
        testDonation.markAsPickedUp();
        testDonation.markAsAvailable();
        assertEquals("available", testDonation.getStatus());
    }

    /*
     * Test switching from all statuses to "pending pick up"
     */
    @Test
    void testMarkAsPending() {
        testDonation.markAsPending();
        testDonation.markAsPending();
        assertEquals("pending pick up", testDonation.getStatus());
        testDonation.markAsPickedUp();
        testDonation.markAsPending();
        assertEquals("pending pick up", testDonation.getStatus());
    }

    /*
     * Test switching from all statuses to "picked up"
     */
    @Test
    void testMarkAsPickedUp() {
        testDonation.markAsPickedUp();
        testDonation.markAsPickedUp();
        assertEquals("picked up", testDonation.getStatus());
        testDonation.markAsPending();
        testDonation.markAsPickedUp();
        assertEquals("picked up", testDonation.getStatus());
    }

    @Test
    void testSetters() {
        testDonation.setQuantity(20);
        assertEquals(20, testDonation.getQuantity());
        testDonation.setStatus("pending pick up");
        assertEquals("pending pick up", testDonation.getStatus());
    }
}
