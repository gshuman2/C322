import java.awt.*;
import java.awt.geom.*;

/**
 A shape that manages its selection state.
 Modified to include x, y, translate and a new constructor.
 */
public abstract class SelectableShape implements SceneShape
{
    public SelectableShape(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setSelected(boolean b)
    {
        selected = b;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void drawSelection(Graphics2D g2)
    {
        translate(1, 1);
        draw(g2);
        translate(1, 1);
        draw(g2);
        translate(-2, -2);
        g2.draw(getBounds());
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void translate(double dx, double dy)
    {
        x += dx;
        y += dy;
    }

    private int x;
    private int y;
    private boolean selected;
}