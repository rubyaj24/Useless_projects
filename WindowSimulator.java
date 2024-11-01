import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WindowSimulator extends JFrame {

    public WindowSimulator() {
        super("Window Simulator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Add a JLabel with the text "this is useless"
        JLabel label = new JLabel("this is useless", SwingConstants.CENTER);
        add(label);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new WindowSimulator();
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

        addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
                    setExtendedState(Frame.ICONIFIED);
                } else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
                    setExtendedState(Frame.MAXIMIZED_BOTH);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WindowSimulator();
            }
        });
    }
}