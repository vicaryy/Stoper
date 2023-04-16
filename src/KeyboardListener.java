
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class KeyboardListener implements KeyListener {
    MouseListener mouseListener;
    Panel panel;
    KeyboardListener(MouseListener mouseListener, Panel panel){
        this.mouseListener = mouseListener;
        this.panel = panel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            panel.mouseListener.circlePressed = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            panel.mouseListener.resetPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            panel.getStartButton().doClick();
            panel.mouseListener.circlePressed = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            panel.getResetButton().doClick();
            panel.mouseListener.resetPressed = false;
        }
    }
}
