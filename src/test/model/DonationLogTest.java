package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DonationLogTest {
    private DonationLog testDonationLog;
    private Donation testDonation1;
    private Donation testDonation2;
    private Donation testDonation3;

    @BeforeEach
    void runBefore() {
        testDonationLog = new DonationLog();
        testDonation1 = new Donation("test donation 1", 10);
        testDonation2 = new Donation("test donation 2", 20);
        testDonation3 = new Donation("test donation 3", 30);
    }

    /*
     * Test constructor and getters
     */
    @Test
    void testConstructor() {
        assertEquals(0, testDonationLog.getNumEntries());
    }

    /*
     * Test adding one donation
     */
    @Test
    void testAddDonation() {
        testDonationLog.addDonation(testDonation1);
        assertEquals(1, testDonationLog.getNumEntries());
    }

    /*
     * Test adding multiple donations
     */
    @Test
    void testAddDonationMultipleTimes() {
        testDonationLog.addDonation(testDonation1);
        testDonationLog.addDonation(testDonation2);
        testDonationLog.addDonation(testDonation3);
        assertEquals(3, testDonationLog.getNumEntries());
    }

    /*
     * Test filtering donation log by status
     */
    @Test
    void testFilterByStatus() {
        testDonationLog.addDonation(testDonation1);
        testDonationLog.addDonation(testDonation2);
        testDonationLog.addDonation(testDonation3);
        testDonation2.markAsPending();
        testDonation3.markAsPickedUp();
        assertEquals(3, testDonationLog.getNumEntries());

        // test filtering by "available" status
        DonationLog availableDonationLog = testDonationLog.filterByStatus("available");
        assertEquals(1, availableDonationLog.getNumEntries());

        // test filtering by "pending pick up" status
        DonationLog pendingDonationLog = testDonationLog.filterByStatus("pending pick up");
        assertEquals(1, pendingDonationLog.getNumEntries());

        // test filtering by "picked up" status
        DonationLog pickedUpDonationLog = testDonationLog.filterByStatus("picked up");
        assertEquals(1, pickedUpDonationLog.getNumEntries());

        // test original donation log is preserved
        assertEquals(3, testDonationLog.getNumEntries());
    }

    @Test
    void testHasDonation() {
        testDonationLog.addDonation(testDonation1);
        assertTrue(testDonationLog.hasDonation("test donation 1"));
        assertFalse(testDonationLog.hasDonation("test donation 2"));
    }

    @Test
    void testGetDonation() {
        testDonationLog.addDonation(testDonation1);
        testDonationLog.addDonation(testDonation2);
        assertEquals(testDonation1, testDonationLog.getDonation("test donation 1"));
    }
}
