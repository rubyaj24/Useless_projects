import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

public class WindowSimulator extends JFrame {

    public WindowSimulator() {
        super("Window Simulator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Set the content pane color to translucent
        getContentPane().setBackground(new Color(0, 0, 0, 128));
        setLayout(new BorderLayout());

        // Add a navigation bar at the top
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Nothing");
        JMenu editMenu = new JMenu("Absolutely nothing");
        JMenu helpMenu = new JMenu("Definitely nothing");
        JMenu uselessProjectsMenu = new JMenu("USELESS PROJECTS");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        menuBar.add(Box.createHorizontalGlue()); // Push the "USELESS PROJECTS" menu to the right
        menuBar.add(uselessProjectsMenu);
        setJMenuBar(menuBar);

        // Add a JLabel with the text "this is useless" and "you're trapped"
        JLabel label = new JLabel("<html><div style='text-align: center;'>this is useless<br>you're trapped</div></html>", SwingConstants.CENTER);
        label.setFont(new Font("Monospaced", Font.ITALIC, 14));
        label.setForeground(Color.BLACK);

        // Create a JPanel with a rounded border
        JPanel roundedPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
            }
        };
        roundedPanel.setOpaque(false);
        roundedPanel.setLayout(new BorderLayout());
        roundedPanel.add(label, BorderLayout.CENTER);
        roundedPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add padding around the content
        JPanel paddingPanel = new JPanel();
        paddingPanel.setOpaque(false);
        paddingPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        paddingPanel.setLayout(new BorderLayout());
        paddingPanel.add(roundedPanel, BorderLayout.CENTER);
        add(paddingPanel, BorderLayout.CENTER);

        // Add an ImageIcon
        ImageIcon icon = new ImageIcon("logo.png");
        this.setIconImage(icon.getImage());

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

    //Ranndomiser
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