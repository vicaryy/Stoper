import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {
    Panel panel;
    boolean startPressed, startEntered;
    boolean resetPressed, resetEntered;
    MouseListener(Panel panel){
        this.panel = panel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == panel.getStartButton()){
            startPressed = true;
        }
        else if(e.getSource() == panel.getResetButton()){
            resetPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource() == panel.getStartButton()){
            startPressed = false;
        }
        else if(e.getSource() == panel.getResetButton()){
            resetPressed = false;
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
    }
}
