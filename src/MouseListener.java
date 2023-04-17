import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {
    Panel panel;
    boolean startPressed, startEntered;
    boolean resetPressed, resetEntered;
    boolean circlePressed;

    MouseListener(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        double distance = Math.sqrt((e.getX() - 150) * (e.getX() - 180) + (e.getY() - 155) * (e.getY() - 170));
        if (e.getSource() == panel.getStartButton()) {
            startPressed = true;
        } else if (e.getSource() == panel.getResetButton()) {
            resetPressed = true;
        }
        else if (distance <= 156 || Double.isNaN(distance)) {
            circlePressed = true;
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        double distance = Math.sqrt((e.getX() - 150) * (e.getX() - 180) + (e.getY() - 155) * (e.getY() - 170));
        if(e.getSource() == panel.getStartButton()){
            startPressed = false;
        }
        else if(e.getSource() == panel.getResetButton()){
            resetPressed = false;
        }
        else if (distance <= 156 || Double.isNaN(distance)) {
            circlePressed = false;
            panel.getStartButton().doClick();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == panel.getStartButton()){
            startEntered = true;
        }
        else if(e.getSource() == panel.getResetButton()){
            resetEntered = true;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == panel.getStartButton()){
            startEntered = false;
        }
        else if(e.getSource() == panel.getResetButton()){
            resetEntered = false;
        }
        else if(e.getSource() == panel.getCirclePanel()){
            circlePressed = false;
        }
    }
}
