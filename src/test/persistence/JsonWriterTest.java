package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Donation;
import model.DonationLog;

/*
 * Referenced from the CPSC 210 JsonSerialization Demo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
@ExcludeFromJacocoGeneratedReport
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDonationLog() {
        try {
            DonationLog donationLog = new DonationLog();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDonationLog.json");
            writer.open();
            writer.write(donationLog);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDonationLog.json");
            donationLog = reader.read();
            assertEquals(0, donationLog.getNumEntries());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDonationLog() {
        try {
            DonationLog donationLog = new DonationLog();
            donationLog.addDonation(new Donation("bananas", 5));
            donationLog.addDonation(new Donation("apples", 10));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDonationLog.json");
            writer.open();
            writer.write(donationLog);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDonationLog.json");
            donationLog = reader.read();
            assertEquals(2, donationLog.getNumEntries());
            checkDonation("bananas", 5, donationLog.getDonation(0));
            checkDonation("apples", 10, donationLog.getDonation(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
