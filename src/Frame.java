import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    Panel panel = new Panel();
    Color frameColor = new Color(24,23,23);
    Frame(){
        //FRAME
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("mac")) {
            this.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);
        }
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(frameColor);
        this.setResizable(false);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
