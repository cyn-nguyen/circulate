package ui;

import java.util.Scanner;

import model.Donation;
import model.DonationLog;

/*
 * Donation application that allows the user to add donations
 * into the donation log, view individual donation entries, and
 * view and filter the entire donation log
 */
public class DonationApp {
    private DonationLog donationLog;
    private Scanner scanner;
    private boolean appIsRunning;

    /*
     * EFFECTS: creates a new instance of the donation application
     *          starting with an empty donation log
     */
    public DonationApp() {
        this.donationLog = new DonationLog();
        this.scanner = new Scanner(System.in);
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
        printDivider();

        while (appIsRunning) {
            displayMenu();

            boolean validInput = true;
            do {
                System.out.print("Please enter your selection (1-5): ");
                String input = this.scanner.nextLine();
                validInput = processInput(input);
            } while (!validInput);
        }

        System.out.println("\nClosing application...");
    }

    /*
     * EFFECTS: displays menu options to the user
     */
    public void displayMenu() {
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
            this.appIsRunning = false;
        } else {
            printDivider();
            System.out.println("*** Error: selection not valid. ***\nPlease select an option from 1-5.");
            printDivider();
            displayMenu();
            return false;
        }
        return true;
    }

    /*
     * MODIFIES: donationLog
     * EFFECTS: adds a donation to the end of the donation log
     */
    public void doAddDonation() throws Exception {
        String name = "";
        int quantity = 0;

        do {
            System.out.print("\nEnter the name of the item: ");
            name = this.scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("\n*** Error: not a valid name. ***");
            }
        } while (name.isEmpty());

        do {
            System.out.print("\nEnter quantity available: ");
            quantity = this.scanner.nextInt();
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
            printDivider();
        }
    }

    /* MODIFIES: Donation
     * EFFECTS: changes the status of a donation according to the
     *          user's input
     */
    public void doChangeDonationStatus() {
        // stub
    }

    /*
     * EFFECTS: filters the donation log according to the donation 
     *          status chosen by the user
     */
    public void doFilterByStatus() {
        // stub
    }

    /*
     * EFFECTS: displays the donation log to the user
     */
    public void displayDonationLog(DonationLog donationLog) {
        // stub
    }

    /*
     * EFFECTS: returns the number of entries in the donation log
     */
    public int countDonations() {
        return -1;
    }

    /*
     * EFFECTS: displays a line that acts as a divider
     */
    public void printDivider() {
        System.out.println("\n-----------------------------------------------------\n");
    }
}
