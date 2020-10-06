import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public interface World {
    void onKey(KeyEvent e);
    void onTick(ActionEvent e);
    void draw(Graphics g);
    boolean terminated();
    void exitMessage();
}


