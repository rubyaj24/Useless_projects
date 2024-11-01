import java.awt.*;
import java.awt.event.*;

public class WindowClosingSimulator extends Frame {

    public WindowClosingSimulator() {
        super("Window Closing Simulator");
        setSize(300, 200);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new WindowClosingSimulator();
            }
        });

        addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
                    setExtendedState(Frame.MAXIMIZED_BOTH);
                } else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
                    setSize(0, 0);
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'x' || e.getKeyChar() == 'X') {
                    dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        new WindowClosingSimulator();
    }
}