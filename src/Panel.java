import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Panel extends JPanel implements ActionListener {
    private int PANEL_WIDTH, PANEL_HEIGHT;
    private long startTime, elapsedTime;
    private int milSeconds, seconds, minutes, hours;
    private boolean isMoreThanMinute, isMoreThan10Minutes, isMoreThanHour, isRunning, isPaused;
    private Timer timer;
    private Color backgroundColor, fontColor;
    private Font milSecondsFont, timeFieldFont;
    private JPanel panelForButtons;
    private JButton start, reset;
    private JTextField milSecondsField, timeField;
    private DecimalFormat df = new DecimalFormat("00");

    Panel() {
        //PARAMETERS
        PANEL_WIDTH = 350;
        PANEL_HEIGHT = 400;

        //FONTS
        milSecondsFont = new Font("Monospaced", 0, 44);
        timeFieldFont = new Font("Monospaced", 0, 74);

        //COLORS
        backgroundColor = new Color(40, 40, 40);
        fontColor = new Color(250, 235, 235);

        //BUTTONS
        timeField = new JTextField("00");
        milSecondsField = new JTextField();

        milSecondsField.setBounds(43, 159, 180, 45);
        milSecondsField.setHorizontalAlignment(SwingConstants.RIGHT);
        milSecondsField.setFont(milSecondsFont);
        milSecondsField.setForeground(fontColor);
        milSecondsField.setBackground(backgroundColor);
        milSecondsField.setBorder(null);


        timeField.setBounds(43, 100, 180, 60);
        timeField.setHorizontalAlignment(SwingConstants.RIGHT);
        timeField.setFont(timeFieldFont);
        timeField.setForeground(fontColor);
        timeField.setBackground(backgroundColor);
        timeField.setBorder(null);

        //PANEL FOR BUTTONS
        panelForButtons = new JPanel();
        panelForButtons.setBounds(0,328,PANEL_WIDTH,60);
        panelForButtons.setBackground(Color.yellow);


        //PANEL
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.add(timeField);
        this.add(milSecondsField);
        this.setBackground(backgroundColor);
        this.add(panelForButtons);
        this.setFocusable(true);


        timer = new Timer(1, e -> {
            elapsedTime = System.currentTimeMillis() - startTime;
            timerUpdate();
        });
        stopwatchStart();
    }

    void stopwatchStart() {
        //HOUR
        //startTime = System.currentTimeMillis() - 3598000;
        //9 HOUR
        //startTime = System.currentTimeMillis() - 35998000;
        //MINUTE
        //startTime = System.currentTimeMillis() - 58000;
        //10 MINUTES
        //startTime = System.currentTimeMillis() - 598000;
        startTime = System.currentTimeMillis();
        timer.start();
    }

    void stopwatchStop() {
        timer.stop();
    }

    void timerSizeCheck(){
        if(!isMoreThanMinute && minutes == 1){
            timeField.setBounds(43, 100, 225, 60);
            milSecondsField.setBounds(43, 159, 225, 45);

            isMoreThanMinute = true;
        }
        else if(!isMoreThan10Minutes && minutes == 10){
            timeField.setBounds(43, 100, 245, 60);
            milSecondsField.setBounds(43, 159, 245, 45);

            isMoreThan10Minutes = true;
        }
        else if(!isMoreThanHour && hours == 1){
            milSecondsField.setBounds(43, 156, 261, 40);
            milSecondsFont = new Font("Monospaced", 0, 34);
            milSecondsField.setFont(milSecondsFont);

            timeField.setBounds(43, 103, 261, 55);
            timeFieldFont = new Font("Monospaced", 0, 60);
            timeField.setFont(timeFieldFont);

            isMoreThanHour = true;
        }
    }


    public void timerUpdate() {
        milSeconds = (int) (elapsedTime % 1000) / 10;
        seconds = (int) (elapsedTime / 1000) % 60;
        minutes = (int) (elapsedTime / 60000) % 60;
        hours =   (int) (elapsedTime / 3600000);

        timerSizeCheck();

        if (minutes == 0 && hours < 1) {
            milSecondsField.setText(df.format(milSeconds));
            timeField.setText(df.format(seconds));
        } else if (minutes >= 1 && hours < 1) {
            milSecondsField.setText(df.format(milSeconds));
            timeField.setText(minutes + ":" + df.format(seconds));
        } else if (hours >= 1) {
            if(hours == 10){
                timer.stop();
            }
            else {
                milSecondsField.setText(df.format(milSeconds));
                timeField.setText(hours + ":" + df.format(minutes) + ":" + df.format(seconds));
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new Color(50,70,70));
        g2d.setStroke(new BasicStroke(10));
        g2d.drawOval(25, 10, 300, 300);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == panelForButtons){
            System.out.println("siema");
        }
    }
}
