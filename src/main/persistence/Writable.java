package persistence;

import org.json.JSONObject;

/*
 * Referenced from the CPSC 210 JsonSerialization Demo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public interface Writable {
    /*
     * EFFECTS: returns this as JSON object
     */
    JSONObject toJson();
}
