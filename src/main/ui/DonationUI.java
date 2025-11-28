package ui;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.DonationLog;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * GUI
 * 
 * Donation application that allows the user to add donations
 * into the donation log, view individual donation entries, and
 * view and filter the entire donation log
 * 
 * All classes used in DonationUI:
    * referenced from the CPSC 210 SmartHome starter
    * https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters
    * and An Introduction to Graphical User Interfaces with Java Swing
    * by Paul Fischer 
    * https://arisikhwan.wordpress.com/wp-content/uploads/2011/03/gui-with-swing.pdf
 */
@ExcludeFromJacocoGeneratedReport
public class DonationUI extends JFrame {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private DonationLog donationLog;
    private CardLayout cardLayout;
    private JPanel cardLayoutPanel;
    private ViewLogPanel viewLogPanel;

    public static void main(String[] args) throws Exception {
        new DonationUI();
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and displays splash screen, then DonationUI frame
     */
    private DonationUI() {
        super("circulate");

        SplashScreen splashScreen = new SplashScreen("./data/loading.gif", 5000);
        try {
            splashScreen.setVisible(true);
            Thread.sleep(5000);
            splashScreen.setVisible(false);
            splashScreen.dispose();
        } catch (InterruptedException e) {
            // pass
        }

        setSize(WIDTH, HEIGHT);

        donationLog = new DonationLog();

        cardLayout = new CardLayout();
        cardLayoutPanel = new JPanel(cardLayout);
        loadMenuOptionPanels();
        add(cardLayoutPanel);
        
        setJMenuBar(loadMenuBar());

        setVisible(true);

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        close();
    }
    
    /*
     * MODIFIES: this
     * EFFECTS: adds menu option panels to this UI
     */
    private void loadMenuOptionPanels() {
        JPanel homePanel = new HomePanel(this);
        cardLayoutPanel.add("Home", homePanel);

        JPanel addDonationPanel = new AddDonationPanel(this);
        cardLayoutPanel.add("Add donation", addDonationPanel);

        JPanel changeStatusPanel = new ChangeStatusPanel(this);
        cardLayoutPanel.add("Change status", changeStatusPanel);

        viewLogPanel = new ViewLogPanel(this);
        cardLayoutPanel.add("View donation log", viewLogPanel);

        JPanel filterLogPanel = new FilterLogPanel(this);
        cardLayoutPanel.add("Filter donation log", filterLogPanel);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds menu bar to this UI
     */
    private JMenuBar loadMenuBar() {
        MenuBar menuBar = new MenuBar(this);
        return menuBar.buildMenuBar();
    }

    /*
     * EFFECTS: shows the home panel
     */
    public void showHomePanel() {
        cardLayout.show(cardLayoutPanel, "Home");
    }

    /*
     * EFFECTS: shows the add a donation panel
     */
    public void showAddDonationPanel() {
        cardLayout.show(cardLayoutPanel, "Add donation");
    }

    /*
     * EFFECTS: shows the change status panel
     */
    public void showChangeStatusPanel() {
        cardLayout.show(cardLayoutPanel, "Change status");
    }

    /*
     * EFFECTS: shows the view donation log panel
     */
    public void showViewPanel() {
        cardLayout.show(cardLayoutPanel, "View donation log");
    }

    /*
     * EFFECTS: shows the filter donation log panel
     */
    public void showFilterPanel() {
        cardLayout.show(cardLayoutPanel, "Filter donation log");
    }


    /*
     * EFFECTS: returns DonationLog controlled by this UI
     */
    public DonationLog getDonationLog() {
        return donationLog;
    }

    /*
     * EFFECTS: notifies the view log panel when a donation is added to the log
     *          so the panel displays the updated donation log
     */
    public void notifyDonationAdded() {
        viewLogPanel.updateTable();
    }

    /*
     * MODIFIES: donation log
     * EFFECTS: loads a donation log from file
     */
    public void loadDonationLog() {
        JsonReader jsonReader = new JsonReader("./data/donationlog.json");
        try {
            this.donationLog = jsonReader.read();
            viewLogPanel = new ViewLogPanel(this);
            cardLayoutPanel.add("View donation log", viewLogPanel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to load donation log", 
                                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*
     * EFFECTS: saves the current donation log to file
     */
    public void saveDonationLog() {
        JsonWriter jsonWriter = new JsonWriter("./data/donationlog.json");
        try {
            jsonWriter.open();
            jsonWriter.write(this.donationLog);
            jsonWriter.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to save donation log", 
                                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: exits the UI and prints to console all events that occurred
     *          since the application started
     */
    public void close() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
                dispose();
                System.exit(0);
            }
        });
    }
}
