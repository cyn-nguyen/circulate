package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Donation;
import model.DonationLog;

/*
 * Represents a reader to read donation log from JSON data stored in file
 * 
 * Referenced from the CPSC 210 JsonSerialization Demo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonReader {
    private String source;

    /*
     * // EFFECTS: constructs reader to read from source file
     */
    public JsonReader(String source) {
        this.source = source;
    }

    /*
     * EFFECTS: returns donation log read from file
     *          throws IOException if an error occurs reading data from file
     */
    public DonationLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDonationLog(jsonObject);
    }

    /*
     * EFFECTS: reads source file as string and returns it
     *          throws IOException if an error occurs reading data from file
     */
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    /*
     * EFFECTS: parses donation log from JSON object and returns it
     */
    private DonationLog parseDonationLog(JSONObject jsonObject) {
        DonationLog donationLog = new DonationLog();
        addDonations(donationLog, jsonObject);
        return donationLog;
    }

    /*
     * MODIFIES: donation log
     * EFFECTS: parses all donations from JSON object and adds them to donation log
     */
    private void addDonations(DonationLog donationLog, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("donations");
        for (Object json : jsonArray) {
            JSONObject nextDonation = (JSONObject) json;
            addDonation(donationLog, nextDonation);
        }
    }

    /*
     * MODIFIES: donation log
     * EFFECTS: parses a donation from JSON object and adds it to donation log
     */
    private void addDonation(DonationLog donationLog, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int quantity = jsonObject.getInt("quantity");
        String status = jsonObject.getString("status");
        Donation donation = new Donation(name, quantity);
        donation.setStatus(status);
        donationLog.addDonation(donation);
    }
}
