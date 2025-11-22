package ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

/*
 * Represents a splash screen that will be displayed while the
 * application is loading
 */
@ExcludeFromJacocoGeneratedReport
public class SplashScreen extends JWindow {

    /*
     * REQUIRES: existing image source, duration > 0
     * EFFECTS: creates splash screen with the given image source
     *          that is displayed for the given duration before the
     *          application loads
     */
    public SplashScreen(String imageSource, int duration) {
        ImageIcon image = new ImageIcon(imageSource);
        JLabel imageLabel = new JLabel(image);
        getContentPane().add(imageLabel);
        pack();
        setLocationRelativeTo(null);
    }
}
