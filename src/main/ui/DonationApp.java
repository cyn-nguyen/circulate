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

            //appIsRunning = false;
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
        String name = getValidDonationName();
        int quantity = 0;

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
            printDivider();
        } else {
            printDivider();
            displayDonationStatusMenu();
            System.out.println("\nWhich status would you like to change \"" + name + "\" to?");
            System.out.println("\nCurrent status of \"" + name + "\" is: " + donation.getStatus());
            String status = getValidDonationStatus();
            if (status.equals("1")) {
                donation.markAsAvailable();
            } else if (status.equals("2")) {
                donation.markAsPending();
            } else if (status.equals("3")) {
                donation.markAsPickedUp();
            }
            System.out.println("\nDonation item \"" + name + "\" status successfully changed to \"" + donation.getStatus() + "\"!");
            printDivider();
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
        System.out.println("\nWhich status would you like to filter the donation log by?");
        displayDonationStatusMenu();
        String status = getValidDonationStatus();
        DonationLog filteredDonationLog = this.donationLog.filterByStatus(status);
        displayDonationLog(filteredDonationLog);
    }

    /*
     * EFFECTS: displays the donation log to the user
     */
    public void displayDonationLog(DonationLog donationLog) {
        if (donationLog.getNumEntries() == 0) {
            System.out.println("\n*** Error: donation log has no entries. ***");
            System.out.println("\nTry adding items first!");
            printDivider();
        } else {
            if (donationLog.isFiltered() && donationLog.getNumEntries() < this.donationLog.getNumEntries()) {
                String status = donationLog.getDonation(0).getStatus();
                printDivider();
                System.out.println("Now viewing: donation log filtered by status \"" + status + "\".\n");
            } else {
                printDivider();
                System.out.println("Now viewing: entire donation log\n");
            }
            System.out.println("Name - Quantity - Status\n");
            for (int donationIndex = 0; donationIndex < donationLog.getNumEntries(); donationIndex++) {
                Donation donation = donationLog.getDonation(donationIndex);
                System.out.println(donation.getName() + " " + donation.getQuantity() + " " + donation.getStatus());
            }
            System.out.println("\nTotal number of items: " + donationLog.getNumEntries());
            printDivider();
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
                } else if (status.equals("3")) {
                status = "picked up";
            } else {
                System.out.println("\n*** Error: selection not valid. ***\nPlease select an option from 1-3.");
            }
        }
        }
        return status;
    }

    /*
     * EFFECTS: returns the number of entries in the donation log
     */
    public int countDonations() {
        return this.donationLog.getNumEntries();
    }

    /*
     * EFFECTS: displays a line that acts as a divider
     */
    public void printDivider() {
        System.out.println("\n-----------------------------------------------------\n");
    }
}
