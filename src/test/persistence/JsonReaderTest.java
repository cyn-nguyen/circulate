package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.DonationLog;

/*
 * Referenced from the CPSC 210 JsonSerialization Demo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
@ExcludeFromJacocoGeneratedReport
public class JsonReaderTest extends JsonTest {
    
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DonationLog donationLog = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDonationLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDonationLog.json");
        try {
            DonationLog donationLog = reader.read();
            assertEquals(0, donationLog.getNumEntries());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDonationLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDonationLog.json");
        try {
            DonationLog donationLog = reader.read();
            assertEquals(2, donationLog.getNumEntries());
            checkDonation("oranges", 15, donationLog.getDonation(0));
            checkDonation("pears", 20, donationLog.getDonation(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
