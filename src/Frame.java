import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    Panel panel = new Panel();
    Color frameColor = new Color(30,30,30);
    Frame(){
        //FRAME
        this.getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);
        this.setDefaultCloseOperation(3);
        this.setBackground(frameColor);
        this.setResizable(false);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
