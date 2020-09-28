import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 A class for a composite icon that contains a list of icons.
 */
public class CompositeIcon implements Icon
{
    /**
     Constructs a composite icon
     @param width the width of the composite icon
     @param height the height of the composite icon
     */
    public CompositeIcon(int width, int height)
    {
        this.width = width;
        this.height = height;
        iconList = new ArrayList<Icon>();
        locationList = new ArrayList<Point>();
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
        int i = 0;
        for (Icon s : iconList)
        {
            Point offset = locationList.get(i++);
            s.paintIcon(c, g, x + offset.x, y + offset.y);
        }
    }

    /**
     Adds an icon to this composite icon
     @param icon the icon to add
     @param x the x offset of the icon within the composite
     @param y the y offset of the icon within the composite
     */
    public void addIcon(Icon icon, int x, int y)
    {
        iconList.add(icon);
        locationList.add(new Point(x, y));
    }

    private int width;
    private int height;

    private ArrayList<Icon> iconList;
    private ArrayList<Point> locationList;
}

