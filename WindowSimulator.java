import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class WindowSimulator extends JFrame {

    public WindowSimulator() {
        super("Window Simulator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Set the content pane color to translucent
        getContentPane().setBackground(Color.BLACK); // RGBA, where A is the alpha for transparency

        // Add a JLabel with the text "this is useless" and "you're trapped" on a new line
        JLabel label = new JLabel("<html>this is useless<br>you're trapped</html>", SwingConstants.CENTER);
        add(label);

        // Add a JLabel with the text "USELESS PROJECTS" using * symbol as the title
        JLabel titleLabel = new JLabel("* USELESS PROJECTS *", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

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
                    dispose(); // Close the window;
                } else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
                    setSize(600, 400); // Set to 600x400 size
                    moveToRandomCorner();
                }
            }
        });

        setVisible(true);
    }

    private void moveToRandomCorner() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = getWidth();
        int height = getHeight();
        Random random = new Random();
        int x = random.nextBoolean() ? 0 : screenSize.width - width;
        int y = random.nextBoolean() ? 0 : screenSize.height - height;
        setLocation(x, y);
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