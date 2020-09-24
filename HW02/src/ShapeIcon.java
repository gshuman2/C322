import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 An icon that contains a moveable shape.
 */
public class ShapeIcon implements Icon
{
    public ArrayList<MoveableShape> shapes = new ArrayList<>(); // Create Arraylist to store shapes

    public ShapeIcon(MoveableShape shape,
                     int width, int height)
    {
        this.shape = shape;
        this.width = width;
        this.height = height;
        shapes.add(shape); // add moveable shape to arraylist
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

        for (int i = 0; i < shapes.size();) { //Draw Shapes
            Graphics2D g2 = (Graphics2D)g;
            shape.draw(g2);
        }
    }

    private int width;
    private int height;
    private MoveableShape shape;
}


