import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Panel extends JPanel implements ActionListener {
    MouseListener mouseListener = new MouseListener(this);
    KeyboardListener keyboardListener = new KeyboardListener(mouseListener, this);
    UI ui = new UI(this, mouseListener);
    private final int PANEL_WIDTH, PANEL_HEIGHT;
    private long startTime, elapsedTime;
    private int milSeconds, seconds, minutes, hours, blinkCount;
    private boolean isMoreThanMinute, isMoreThan10Minutes, isMoreThanHour, isRunning, isPaused;
    private final Timer timer, restTimer;
    private final Color backgroundColor, fontColor;
    private Font milSecondsFont, timeFieldFont;
    private final JPanel circlePanel;
    private final JButton startButton, resetButton;
    private final JTextField milSecondsField, timeField;
    private final DecimalFormat df = new DecimalFormat("00");

    Panel() {

        //PARAMETERS
        PANEL_WIDTH = 350;
        PANEL_HEIGHT = 425;

        //FONTS
        milSecondsFont = new Font("Monospaced", Font.PLAIN, 44);
        timeFieldFont = new Font("Monospaced", Font.PLAIN, 74);

        //COLORS
        backgroundColor = new Color(31, 31, 31);
        fontColor = new Color(227, 227, 227);


        //PANELS
        circlePanel = new JPanel();
        circlePanel.setBounds(35,20,280,280);
        circlePanel.setBackground(Color.yellow);
        circlePanel.setOpaque(false);
        circlePanel.addMouseListener(mouseListener);

        //BUTTONS
        startButton = new JButton("▶");
        startButton.setFont(new Font(".AppleSystemUIFont", Font.PLAIN,23));
        startButton.setForeground(Color.BLACK);
        startButton.addActionListener(this);
        startButton.addMouseListener(mouseListener);
        startButton.addKeyListener(keyboardListener);
        startButton.setBounds(131,325,90,90);
        startButton.setBorderPainted(false);
        startButton.setOpaque(false);

        resetButton = new JButton("↻");
        resetButton.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 23));
        resetButton.addActionListener(this);
        resetButton.addMouseListener(mouseListener);
        resetButton.addKeyListener(keyboardListener);
        resetButton.setBounds(48,343,54,54);
        resetButton.setOpaque(false);
        resetButton.setBorderPainted(false);
        resetButton.setVisible(false);

        //TEXT FIELDS
        timeField = new JTextField("00");
        milSecondsField = new JTextField("00");

        milSecondsField.setBounds(163, 169, 60, 45);
        milSecondsField.setHorizontalAlignment(SwingConstants.RIGHT);
        milSecondsField.setFont(milSecondsFont);
        milSecondsField.setDisabledTextColor(fontColor);
        milSecondsField.setBackground(backgroundColor);
        milSecondsField.setBorder(null);
        milSecondsField.setEditable(false);
        milSecondsField.setEnabled(false);
        milSecondsField.addMouseListener(mouseListener);


        timeField.setBounds(128, 110, 95, 60);
        timeField.setHorizontalAlignment(SwingConstants.RIGHT);
        timeField.setFont(timeFieldFont);
        timeField.setDisabledTextColor(fontColor);
        timeField.setBackground(backgroundColor);
        timeField.setBorder(null);
        timeField.setEditable(false);
        timeField.setEnabled(false);
        timeField.addMouseListener(mouseListener);


        //PANEL
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.add(circlePanel);
        this.add(timeField);
        this.add(milSecondsField);
        this.add(startButton);
        this.add(resetButton);
        this.addMouseListener(mouseListener);
        this.addKeyListener(keyboardListener);
        this.setBackground(backgroundColor);
        this.setFocusable(true);

        restTimer = new Timer(10, e -> repaint());
        restTimer.start();

        timer = new Timer(10, e -> timerUpdate());
    }

    void stopwatchStart() {
        isRunning = true;
        startButton.setText("⏸");
        startButton.setFont(new Font(".AppleSystemUIFont", Font.PLAIN,25));
        resetButton.setVisible(true);
        milSecondsField.setVisible(true);
        timeField.setVisible(true);
        if(!isPaused) {
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
            restTimer.stop();
        }
        isPaused = false;
    }

    void stopwatchReset(){
        milSecondsField.setText("00");
        timeField.setText("00");
        milSecondsField.setBounds(163, 169, 60, 45);
        timeField.setBounds(128, 110, 95, 60);
        startButton.setText("▶");
        isRunning = false;
        isPaused = false;
        isMoreThanMinute = false;
        isMoreThan10Minutes = false;
        isMoreThanHour = false;
        resetButton.setVisible(false);
        milSecondsField.setVisible(true);
        timeField.setVisible(true);
        timer.stop();
        restTimer.start();
    }

    void stopwatchPause() {
        isRunning = false;
        isPaused = true;
        startButton.setText("▶");
        startButton.setFont(new Font(".AppleSystemUIFont", Font.PLAIN,22));
    }

    void timerSizeCheck(){
        if(!isMoreThanMinute && minutes == 1){
            timeField.setBounds(78, 110, 190, 60);
            milSecondsField.setBounds(211, 169, 57, 45);

            isMoreThanMinute = true;
        }
        else if(!isMoreThan10Minutes && minutes == 10){
            timeField.setBounds(58, 110, 230, 60);
            milSecondsField.setBounds(230, 169, 58, 45);

            isMoreThan10Minutes = true;
        }
        else if(!isMoreThanHour && hours == 1){
            milSecondsField.setBounds(257, 166, 47, 40);
            milSecondsFont = new Font("Monospaced", Font.PLAIN, 34);
            milSecondsField.setFont(milSecondsFont);

            timeField.setBounds(48, 113, 256, 55);
            timeFieldFont = new Font("Monospaced", Font.PLAIN, 60);
            timeField.setFont(timeFieldFont);

            isMoreThanHour = true;
        }
    }


    public void timerUpdate() {
        repaint();
        if(isRunning) elapsedTime = System.currentTimeMillis() - startTime;
        else if(isPaused) {
            startTime = System.currentTimeMillis() - elapsedTime;
            blinking();
            return;
        }

        milSeconds = (int) (elapsedTime % 1000) / 10;
        seconds = (int) (elapsedTime / 1000) % 60;
        minutes = (int) (elapsedTime / 60000) % 60;
        hours = (int) (elapsedTime / 3600000);

        timerSizeCheck();

        if (minutes == 0 && hours < 1) {
            milSecondsField.setText(df.format(milSeconds));
            timeField.setText(df.format(seconds));
        } else if (minutes >= 1 && hours < 1) {
            milSecondsField.setText(df.format(milSeconds));
            timeField.setText(minutes + ":" + df.format(seconds));
        } else if (hours >= 1) {
            if (hours == 10) {
                timer.stop();
            } else {
                milSecondsField.setText(df.format(milSeconds));
                timeField.setText(hours + ":" + df.format(minutes) + ":" + df.format(seconds));
            }
        }
    }

    public void blinking(){
        blinkCount++;
        if(mouseListener.circlePressed){
            milSecondsField.setVisible(true);
            timeField.setVisible(true);
        }
        else if(blinkCount < 40) {
            milSecondsField.setVisible(false);
            timeField.setVisible(false);
        }
        else if(blinkCount > 40 && blinkCount < 80){
            milSecondsField.setVisible(true);
            timeField.setVisible(true);
        }
        else if(blinkCount > 80){
            blinkCount = 0;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Włącza antyaliasing, wygładza okręgi
        ui.paintPlayButton(g2d);
        ui.paintResetButton(g2d);
        ui.paintCircle(g2d);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (isRunning) stopwatchPause();
            else stopwatchStart();
        }
        else if (e.getSource() == resetButton){
            stopwatchReset();
        }
    }


    public boolean isRunning() {
        return isRunning;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }
    public JPanel getCirclePanel() {
        return circlePanel;
    }
}
