import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    Panel panel = new Panel();
    Frame(){
        this.setTitle("Stoper");
        this.setSize(600,600);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(3);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
