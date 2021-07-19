import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App extends JPanel {
    // Setup
    private static int WIDTH = 800, HEIGHT = 800;
    private static final int UPS = 60, FPS = 560;
    private static JFrame frame = new JFrame();

    private static final boolean RENDER_TIME = true;
    private static final boolean running = true;

    public App() {
        setFocusable(true);
               
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {
        
            }

            public void keyTyped(KeyEvent e) {

            }
        });

        addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }   

            public void mouseClicked(MouseEvent e) {

            }
        });
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

    }

    public void update() {
        WIDTH = this.getWidth();
        HEIGHT = this.getHeight();

    }

    public void run() {
        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / UPS;
        final double timeF = 1000000000 / FPS;
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                repaint();
                update();
                ticks++;
                deltaU--;
            }

            if (deltaF >= 1) {
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                if (RENDER_TIME) {
                    System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
                }

                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }
    }

    public static void main(String[] args) {
        App app = new App();

        app.setDoubleBuffered(true);
        app.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame.add(app);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        app.run();
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
