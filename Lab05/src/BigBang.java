import javax.swing.Timer;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.PointerInfo;
import java.awt.MouseInfo;
import javax.swing.JLabel;

public class BigBang extends JComponent implements ActionListener, KeyListener {
    World ws;
    Timer t;
    public BigBang(World world) {
        this.ws = world;
        this.addKeyListener(this);
        // Ensure that this component receives the keyboard input
        this.setFocusable(true);
        this.requestFocus();

    }

    /**
     This is the function that will start the big bang
     */
    public void start(int tDelay, int width, int height) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(width, height+frame.getInsets().top);

        frame.add(this);


        this.t = new Timer(tDelay, this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.t.start();
    }

    /**
     These are the methods to be implemented from KeyListener
     */
    public void keyPressed(KeyEvent e) {
        this.ws.onKey(e);
        this.repaint();
    }
    public void keyReleased(KeyEvent e) {}
    /**
     This is the racket equivalent of on-key. on-key takes in a function that
     takes in the world and the key. The handler will already have access to the
     world, so we need only provide the key and repaint after the world is updated
     */
    public void keyTyped(KeyEvent e) {
        this.ws.onKey(e);
        this.repaint();
    }

    /**
     These are the methods to be implemented from ActionListener
     Racket has an on-tick clause that takes in a function that handles the world
     state on every tick and updates it accordingly. On every tick, we need to make
     sure that the world state has not reached a terminal position.
     */
    public void actionPerformed(ActionEvent e) {

        if (this.ws.terminated()) {
            this.t.stop();
            this.ws.exitMessage();
        }
        else {
            this.ws.onTick(e);
        }
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        this.ws.draw(g);
    }
}