import java.awt.*;

public class UI {
    Panel panel;
    MouseListener mouseListener;
    Color startButtonColor, startButtonColorPressed, startButtonColorEntered;
    Color resetButtonColor, resetButtonColorPressed, resetButtonColorEntered;
    Color circleColor, circleColorPressed;

    UI(Panel panel, MouseListener mouseListener) {
        this.panel = panel;
        this.mouseListener = mouseListener;

        //COLORS
        startButtonColor = new Color(168, 200, 251);
        startButtonColorPressed = new Color(168, 150, 251);
        startButtonColorEntered = new Color(168, 180, 251);
        resetButtonColor = new Color(0, 90, 30);
        resetButtonColorPressed = new Color(0, 120, 30);
        resetButtonColorEntered = new Color(0, 93, 30);
        circleColor = new Color(50, 70, 70);
        circleColorPressed = new Color(80, 90, 90);
    }

    void paintPlayButton(Graphics2D g2d) {
        g2d.setPaint(startButtonColor);
        if (mouseListener.startEntered) {
            g2d.setPaint(startButtonColorEntered);
        }
        if (mouseListener.startPressed && mouseListener.startEntered) {
            g2d.setPaint(startButtonColorPressed);
        }

        if (!panel.isRunning()) {
            g2d.fillOval(131, 325, 90, 90);
        } else {
            g2d.setStroke(new BasicStroke(12));
            g2d.fillRect(132, 327, 87, 86);
            g2d.setPaint(panel.getBackgroundColor());
            g2d.drawOval(118, 316, 115, 108);
        }
    }

    void paintResetButton(Graphics2D g2d) {
        if (panel.isRunning() || panel.isPaused()) {
            g2d.setPaint(resetButtonColor);
            if (mouseListener.resetEntered) {
                g2d.setPaint(resetButtonColorEntered);
            }
            if (mouseListener.resetPressed && mouseListener.resetEntered) {
                g2d.setPaint(resetButtonColorPressed);
            }
            g2d.fillOval(50, 345, 50, 50);
        }
    }

    void paintCircle(Graphics2D g2d) {
        g2d.setPaint(circleColor);
        if (mouseListener.circlePressed) {
            g2d.setPaint(circleColorPressed);
        }
        g2d.setStroke(new BasicStroke(10));
        g2d.drawOval(25, 10, 300, 300);
    }
}
