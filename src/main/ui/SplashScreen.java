package ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class SplashScreen extends JWindow {

    public SplashScreen(String imageSource, int duration) {
        ImageIcon image = new ImageIcon(imageSource);
        JLabel imageLabel = new JLabel(image);
        getContentPane().add(imageLabel);
        pack();
        setLocationRelativeTo(null);
    }
}
