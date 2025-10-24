package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Donation;

/*
 * Referenced from the CPSC 210 JsonSerialization Demo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkDonation(String name, int quantity, String status, Donation donation) {
        assertEquals(name, donation.getName());
        assertEquals(quantity, donation.getQuantity());
        assertEquals("available", donation.getStatus());
    }
}
