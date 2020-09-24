import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 An icon that can be drawn
 */
public class DrawableIcon implements Icon
{
    /**
     An icon that can be drawn
     @param d a Drawable object to draw
     @param width its width
     @param height its height
     */
    public DrawableIcon(Drawable d, int width, int height)
    {
        this.theDrawable = d;
        this.width = width;
        this.height = height;
    }

    public int getIconWidth()
    {
        return width;
    }

    public int getIconHeight()
    {
        return height;
    }

    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2 = (Graphics2D)g;
        theDrawable.draw(g2);
    }

    private int width;
    private int height;
    private Drawable theDrawable;
}

