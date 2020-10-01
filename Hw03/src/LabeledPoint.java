import java.awt.*;

public class LabeledPoint extends java.awt.Point {
    private String text;
    int x, y;
    public LabeledPoint(int x, int y, String text) {
        this.x = x;
        this.y = y;
        this.text = text;
        }

        public void draw(Graphics g) {
            g.drawOval(this.x,this.y, 3,3);
            g.drawString(this.text, this.x, this.y);
        }



}
