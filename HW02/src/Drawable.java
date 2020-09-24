import java.awt.*;

/**
 A shape that can be moved around.
 */
public interface Drawable
{
    /**
     Draws the shape.
     @param g2 the graphics context
     */
    void draw(Graphics2D g2);
}