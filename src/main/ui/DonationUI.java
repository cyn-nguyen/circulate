package ui;

import java.awt.CardLayout;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.DonationLog;

/*
 * GUI
 * 
 * Referenced from the CPSC 210 SmartHome starter
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
    private JPanel cardLayoutPanel;

    public static void main(String[] args) throws Exception {
        new DonationUI();
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and displays DonationUI frame
     */
    private DonationUI() {
        super("circulate");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        donationLog = new DonationLog();

        CardLayout cardLayout = new CardLayout();
        cardLayoutPanel = new JPanel(cardLayout);
        loadMenuOptionPanels();
        add(cardLayoutPanel);
        
        setJMenuBar(loadMenuBar());

        setVisible(true);
    }
    
    /*
     * MODIFIES: this
     * EFFECTS: adds menu option panels to this UI
     */
    private void loadMenuOptionPanels() {
        JPanel homePanel = new JPanel();
        cardLayoutPanel.add(homePanel);
        JLabel welcomeLabel1 = new JLabel("Welcome to circulate: a donation tracking application");
        homePanel.add(welcomeLabel1);
        JLabel welcomeLabel2 = new JLabel("Please select an option from the menu bar");
        homePanel.add(welcomeLabel2);

        JPanel addDonationPanel = new JPanel();
        cardLayoutPanel.add(addDonationPanel);

        JPanel viewLogPanel = new JPanel();
        cardLayoutPanel.add(viewLogPanel);

        JPanel filterLogPanel = new JPanel();
        cardLayoutPanel.add(filterLogPanel);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds menu bar to this UI
     */
    private JMenuBar loadMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem loadFromFile = new JMenuItem("Load donation log from file");
        fileMenu.add(loadFromFile);
        JMenuItem saveToFile = new JMenuItem("Save donation log to file");
        fileMenu.add(saveToFile);
        JMenuItem quit = new JMenuItem("Quit");
        fileMenu.add(quit);
         
        JMenu actionsMenu = new JMenu("Actions");
        menuBar.add(actionsMenu);

        JMenuItem addDonation = new JMenuItem("Add a donation");
        actionsMenu.add(addDonation);
        JMenuItem viewLog = new JMenuItem("View donation log");
        actionsMenu.add(viewLog);
        JMenuItem filterLog = new JMenuItem("Filter donation log by item status");
        actionsMenu.add(filterLog);

        return menuBar;
    }

    /*
     * EFFECTS: returns DonationLog controlled by this UI
     */
    private DonationLog getDonationLog() {
        return donationLog;
    }
}
