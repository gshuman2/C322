import java.awt.*;
import java.awt.geom.*;

/**
 A house shape.
 Modified to remove x, y, and translate.
 */
public class HouseShape extends SelectableShape
{
    /**
     Constructs a house shape.
     @param x the left of the bounding rectangle
     @param y the top of the bounding rectangle
     @param width the width of the bounding rectangle
     */
    public HouseShape(int x, int y, int width)
    {
        super(x, y);
        this.width = width;
    }

    public void draw(Graphics2D g2)
    {
        int x = getX();
        int y = getY();

        Rectangle2D.Double base
                = new Rectangle2D.Double(x, y + width, width, width);

        // the left bottom of the roof
        Point2D.Double r1
                = new Point2D.Double(x, y + width);
        // the top of the roof
        Point2D.Double r2
                = new Point2D.Double(x + width / 2, y);
        // the right bottom of the roof
        Point2D.Double r3
                = new Point2D.Double(x + width, y + width);

        Line2D.Double roofLeft
                = new Line2D.Double(r1, r2);
        Line2D.Double roofRight
                = new Line2D.Double(r2, r3);
        g2.draw(base);
        g2.draw(roofLeft);
        g2.draw(roofRight);
    }

    public boolean contains(Point2D p)
    {
        int x = getX();
        int y = getY();

        return x <= p.getX() && p.getX() <= x + width
                && y <= p.getY() && p.getY() <= y + 2 * width;
    }

    public Rectangle getBounds()
    {
        return new Rectangle(getX(),getY(),width,width);
    }
    private int width;
}