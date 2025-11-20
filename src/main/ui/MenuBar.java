package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
    private final DonationUI app;

    public MenuBar(DonationUI app) {
        this.app = app;
        JMenuBar menuBar = buildMenuBar();
    }

    public JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(buildFileMenu());
        menuBar.add(buildActionsMenu());

        return menuBar;
    }

    private JMenu buildFileMenu() {
        JMenu fileMenu = new JMenu("File");

        fileMenu.add(addMenuItem("Home", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.showHomePanel();
            }
        }));

        // fileMenu.add(addMenuItem("Load donation log from file"));
        // fileMenu.add(addMenuItem("Save donation log to file"));
        // fileMenu.add(addMenuItem("Quit"));

        return fileMenu;
    }

    private JMenu buildActionsMenu() {
        JMenu actionsMenu = new JMenu("Actions");

        actionsMenu.add(addMenuItem("Add a donation", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.showAddDonationPanel();
            }
        }));

        actionsMenu.add(addMenuItem("View donation log", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.showViewPanel();
            }
        }));

        actionsMenu.add(addMenuItem("Filter donation log by item status", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.showFilterPanel();
            }
        }));

        return actionsMenu;
    }

    private JMenuItem addMenuItem(String action, ActionListener al) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.addActionListener(al);
        return menuItem;
    }
}