package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Donation;
import model.DonationLog;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * CONSOLE UI
 * Donation application that allows the user to add donations
 * into the donation log, view individual donation entries, and
 * view and filter the entire donation log
 */
@ExcludeFromJacocoGeneratedReport
public class DonationApp {
    private static final String JSON_STORE = "./data/donationlog.json";
    private DonationLog donationLog;
    private Scanner scanner;
    private boolean appIsRunning;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    /*
     * EFFECTS: creates a new instance of the donation application
     *          starting with an empty donation log
     */
    public DonationApp() {
        this.donationLog = new DonationLog();
        this.scanner = new Scanner(System.in);
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        runDonationApp();
    }

    /*
     * MODIFIES: this
     * EFFECTS: runs the donation application
     */
    public void runDonationApp() {
        this.appIsRunning = true;

        printDivider();
        System.out.println("\nWelcome to circulate: a donation tracking application\n");

        loadDonationLog();

        while (appIsRunning) {
            displayMenu();
            System.out.print("Please enter your selection (1-5): ");
            String input = this.scanner.nextLine();
            appIsRunning = processInput(input);
        } 
        System.out.println("\nClosing application...");
    }

    /*
     * EFFECTS: displays menu options to the user
     */
    public void displayMenu() {
        printDivider();
        System.out.println("\nMenu options:\n");
        System.out.println("\t1. Add a donation entry");
        System.out.println("\t2. Change the status of a donation entry");
        System.out.println("\t3. View the entire donation log");
        System.out.println("\t4. Filter the donation log by donation status");
        System.out.println("\t5. Quit application\n");
        printDivider();
    }

    /*
     * EFFECTS: reads user input from the menu and returns a boolean depending 
     *          on whether or not the input was valid and successfully processed
     */
    public boolean processInput(String input) {
        input = input.trim();
        if (input.equals("1")) {
            try {
                doAddDonation();
            } catch (Exception e) {
                System.out.println("*** Error: donation was not added to donation log ***");
            }
        } else if (input.equals("2")) {
            doChangeDonationStatus();
        } else if (input.equals("3")) {
            displayDonationLog(this.donationLog);
        } else if (input.equals("4")) {
            doFilterByStatus();
        } else if (input.equals("5")) {
            saveDonationLog();
            return false;
        } else {
            printDivider();
            System.out.println("*** Error: selection not valid. ***\nPlease select an option from 1-5.");
        }
        return true;
    }

    /*
     * MODIFIES: donationLog
     * EFFECTS: adds a donation to the end of the donation log
     */
    public void doAddDonation() throws Exception {
        String name = getValidDonationName();
        int quantity = 0;

        do {
            System.out.print("\nEnter quantity available: ");
            quantity = this.scanner.nextInt();
            this.scanner.nextLine();
            if (quantity <= 0) {
                System.out.println("\n*** Error: quantity must be greater than 0. ***");
            }
        } while (quantity <= 0);
        
        Donation donation = new Donation(name, quantity);
        this.donationLog.addDonation(donation);

        if (!this.donationLog.hasDonation(name)) {
            throw new Exception();
        } else {
            System.out.println("\nDonation \"" + name + "\" successfully added to donation log!");
        }
    }

    /* 
     * MODIFIES: Donation
     * EFFECTS: changes the status of a donation according to the
     *          user's input
     */
    public void doChangeDonationStatus() {
        System.out.println("\nWhich donation item's status would you like to change?");
        String name = getValidDonationName();

        Donation donation = this.donationLog.getDonation(name);

        if (donation == null) {
            System.out.println("\n*** Error: donation \"" + name + "\" not in donation log. ***");
            System.out.println("\nTry adding the item first!");
        } else {
            printDivider();
            displayDonationStatusMenu();
            System.out.println("\nWhich status would you like to change \"" + name + "\" to?");
            System.out.println("\nCurrent status of \"" + name + "\" is: " + donation.getStatus());
            String status = getValidDonationStatus();
            donation.setStatus(status);
            System.out.println("\nDonation item \"" + name + "\" status successfully changed to \"" 
                                + donation.getStatus() + "\"!");
        }
    }

    /*
     * EFFECTS: displays the options for donation status as a menu
     */
    public void displayDonationStatusMenu() {
        System.out.println("\t1. Available");
        System.out.println("\t2. Pending pick up");
        System.out.println("\t3. Picked up");
        printDivider();
    }

    /*
     * EFFECTS: filters the donation log according to the donation 
     *          status chosen by the user
     */
    public void doFilterByStatus() {
        System.out.println("\nWhich status would you like to filter the donation log by?\n");
        displayDonationStatusMenu();
        String status = getValidDonationStatus();
        DonationLog filteredDonationLog = this.donationLog.filterByStatus(status);
        if (filteredDonationLog.getNumEntries() == 0) {
            System.out.println("\nThere are no items with status \"" + status + "\".");
        } else {
            displayDonationLog(filteredDonationLog);
        }
    }

    /*
     * EFFECTS: displays the donation log to the user
     */
    public void displayDonationLog(DonationLog donationLog) {
        if (donationLog.getNumEntries() == 0) {
            System.out.println("\n*** Error: donation log has no entries. ***\nTry adding items first!");
        } else {
            String status = donationLog.getDonation(0).getStatus();
            if (donationLog.isFiltered() && donationLog.getNumEntries() < this.donationLog.getNumEntries()) {
                printDivider();
                System.out.println("NOW VIEWING: donation log filtered by status \"" + status + "\"");
                System.out.println("\nShowing " + donationLog.getNumEntries() + " of " 
                                    + this.donationLog.getNumEntries() + " items");
            } else {
                printDivider();
                System.out.println("NOW VIEWING: entire donation log\n");
                if (donationLog.isFiltered()) {
                    System.out.println(donationLog.getNumEntries() + " of " 
                                        + this.donationLog.getNumEntries() + " items are \"" + status + "\"");
                }
            }
            System.out.println("\nName - Quantity - Status\n");
            for (int donationIndex = 0; donationIndex < donationLog.getNumEntries(); donationIndex++) {
                Donation donation = donationLog.getDonation(donationIndex);
                System.out.println(donation.getName() + " " + donation.getQuantity() + " " + donation.getStatus());
            }
        }
    }

    /*
     * EFFECTS: returns a valid name for a donation as inputted by the user
     */
    public String getValidDonationName() {
        String name = "";

        do {
            System.out.print("\nEnter the name of the item: ");
            name = this.scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("\n*** Error: not a valid name. ***");
            }
        } while (name.isEmpty());

        return name;
    }

    /*
     * EFFECTS: returns a valid status selection from the user according
     *          to options listed in displayDonationStatusMenu()
     */
    public String getValidDonationStatus() {
        String status = "";
        boolean validStatus = false;
        while (!validStatus) {
            System.out.print("\nPlease select a status (1-3): ");
            status = this.scanner.nextLine().trim();
            if (status.equals("1") || status.equals("2") || status.equals("3")) {
                validStatus = true;
                if (status.equals("1")) {
                    status = "available";
                } else if (status.equals("2")) {
                    status = "pending pick up";
                } else {
                    status = "picked up";
                }
            } else {
                System.out.println("\n*** Error: selection not valid. ***\nPlease select an option from 1-3.");
            }
        }
        return status;
    }

    /*
     * EFFECTS: displays a line that acts as a divider
     */
    public void printDivider() {
        System.out.println("\n-----------------------------------------------------\n");
    }

    /*
     * EFFECTS: saves the donation log to file if the user chooses
     */
    private void saveDonationLog() {
        System.out.print("\nWould you like to save updates made to this donation log to file? (y/n): ");
        String input = getValidInput();
        if (input.equals("y")) {
            try {
                jsonWriter.open();
                jsonWriter.write(this.donationLog);
                jsonWriter.close();
                System.out.println("\nSaved most recent donation log with " 
                                    + this.donationLog.getNumEntries() + " items to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                System.out.println("\nUnable to write to file: " + JSON_STORE);
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: loads donation log from file if the user chooses
     */
    private void loadDonationLog() {
        System.out.print("\nWould you like to load the previous donation log from file? (y/n): ");
        String input = getValidInput();
        if (input.equals("y")) {
            try {
                this.donationLog = jsonReader.read();
                System.out.println("\nLoaded most recent donation log with " 
                                    + this.donationLog.getNumEntries() + " items from " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("\nUnable to read from file: " + JSON_STORE);
            }
        }
    }

    /*
     * EFFECTS: gets and returns valid input from user
     */
    private String getValidInput() {
        boolean validInput = false;
        String input = "";
        while (!validInput) {
            input = this.scanner.nextLine().toLowerCase().trim();
            if (input.equals("y") || input.equals("n")) {
                validInput = true;
            } else {
                System.out.print("\nPlease enter a valid selection (y/n): ");
            }
        } 
        return input;
    }
}
