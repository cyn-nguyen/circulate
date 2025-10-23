package persistence;

import java.io.IOException;

import org.json.JSONObject;

import model.DonationLog;

/*
 * Represents a reader to read donation log from JSON data stored in file
 * 
 * Referenced from the CPSC 210 JsonSerialization Demo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonReader {
    /*
     * // EFFECTS: constructs reader to read from source file
     */
    public JsonReader(String source) {
        // stub
    }

    /*
     * EFFECTS: returns donation log read from file
     *          throws IOException if an error occurs reading data from file
     */
    public DonationLog read() throws IOException {
        return null;
    }

    /*
     * EFFECTS: reads source file as string and returns it
     *          throws IOException if an error occurs reading data from file
     */
    public String readFile(String source) throws IOException {
        return "";
    }

    /*
     * EFFECTS: parses donation log from JSON object and returns it
     */
    public DonationLog parseDonationLog(JSONObject jsonObject) {
        return null;
    }

    /*
     * MODIFIES: donation log
     * EFFECTS: parses all donations from JSON object and adds them to donation log
     */
    public void addDonations(DonationLog donationLog, JSONObject jsonObject) {
        // stub
    }

    /*
     * MODIFIES: donationg log
     * EFFECTS: parses a donation from JSON object and adds it to donation log
     */
    public void addDonation(DonationLog donationLog, JSONObject jsonObject) {
        // stub
    }
}
