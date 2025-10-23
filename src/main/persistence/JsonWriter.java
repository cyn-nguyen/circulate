package persistence;

import java.io.FileNotFoundException;

import model.DonationLog;

/*
 * Represents a JSON writer to write data to file
 * 
 * Referenced from the CPSC 210 JsonSerialization Demo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonWriter {
    /*
     * EFFECTS: constructs writer to write to destination file
     */
    public JsonWriter(String destination) {
        // stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: opens writer
     *          throws FileNotFoundException if destination file cannot
     *          be opened for writing
     */
    public void open() throws FileNotFoundException {
        // stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes JSON representation of donation log to file
     */
    public void write(DonationLog donationLog) {
        // stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: closes writer
     */
    public void close() {
        // stub
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes string to file
     */
    public void saveToFile(String json) {
        // stub
    }
}
