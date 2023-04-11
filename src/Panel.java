import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Panel extends JPanel implements ActionListener {
    private int PANEL_WIDTH = 350;
    private int PANEL_HEIGHT = 450;
    private int milSeconds;
    private int seconds;
    private int minutes;
    private int hours;
    private Timer timer;
    Color backgroundColor;
    Color fontColor;
    Font milSecondsFont = new Font("Monospaced", 0, 38);
    Font timeFieldFont = new Font("Monospaced", 0, 60);
    JButton start;
    JButton reset;
    JTextField milSecondsField;
    JTextField timeField;
    DecimalFormat df = new DecimalFormat("00");
    Panel(){
        //COLORS
        backgroundColor = new Color(40,40,40);
        fontColor = new Color(200,180,190);

        //BUTTONS
        timeField = new JTextField("00");
        milSecondsField = new JTextField();

        milSecondsField.setBounds(165,180,60,50);
        milSecondsField.setFont(milSecondsFont);
        milSecondsField.setForeground(fontColor);
        milSecondsField.setBackground(backgroundColor);
        milSecondsField.setEditable(false);
        milSecondsField.setEnabled(false);

        timeField.setBounds(35,120,280, 60);
        timeField.setHorizontalAlignment(SwingConstants.CENTER);
        timeField.setFont(timeFieldFont);
        timeField.setForeground(fontColor);
        timeField.setBackground(backgroundColor);
        //timeField.setBorder(null);
        timeField.setBackground(backgroundColor);


        //PANEL
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.add(timeField);
        this.add(milSecondsField);
        this.setBackground(backgroundColor);
        this.setFocusable(true);

        timer = new Timer(10,this);
        stopwatchStart();
    }

    public void stopwatchStart(){
        timer.start();
    }

    public void stopwatchStop(){
        timer.stop();
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(fontColor);
        g2d.setStroke(new BasicStroke(8));
        g2d.drawOval(25,10,300,300);
    }

    public void timerUpdate(){
        milSecondsField.setText(df.format(milSeconds));
        timeField.setText(df.format(seconds));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        milSeconds++;
        if(milSeconds==100){
            milSeconds = 0;
            seconds++;
        }
        else if(seconds==60){
            seconds = 0;
            minutes++;
        }
        else if(minutes==60){
            minutes = 0;
            hours++;
        }
        else if(hours==100){
            stopwatchStop();
        }
        timerUpdate();
    }
}
