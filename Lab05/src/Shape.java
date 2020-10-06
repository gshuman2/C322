import java.util.Random;
import java.util.Arrays;
import java.awt.Point;
import java.awt.Color;

/**
 The shape class should be able to create new shapes built from tetrominos.
 As the game progresses, the shape itself may get decomposed (since lines may
 be removed), but Tetrominos will have their own x and y postion and will not
 be necessarily affected if another tetromino in the shape is removed
 */

public class Shape {

    /**
     Enumeration of shapes where the Nillshape represents no shape.
     Our main program will color positions based on the type of tetromino
     present in it.
     */

    protected enum Tetromino {
        NillShape, SShape, ZShape, LineShape, TShape, SquareShape,
        ReverseLShape, LShape
    }

    private static final Color[] colors = {Color.BLACK, Color.GREEN, Color.RED, Color.CYAN,
            new Color(192, 0, 255), Color.YELLOW, Color.ORANGE, Color.BLUE
    };

    /**
     All rotation positions for each shape is stored to ensure proper
     rotation behavior.
     */
    private int numConfigs;
    private final int[][][][] configs = {
            { // NillShape only has one configuration
                    {{0, 0}, {0, 0}, {0, 0}, {0, 0}}
            },
            { // ZShape has two
                    {{-1, 0}, {0, 0}, {0, -1}, {1, -1}},
                    {{0, -1}, {0, 0}, {1, 0}, {1, 1}}
            },
            { //SShape has two
                    {{-1, -1}, {0, -1}, {0, 0}, {1, 0}},
                    {{-1, 1}, {-1, 0}, {0, 0}, {0, -1}}
            },
            { // LineShape has two
                    {{-2, 0}, {-1, 0}, {0, 0}, {1, 0}},
                    {{0, -2}, {0, -1}, {0, 0}, {0, 1}}
            },
            { // TShape has four
                    {{-1, 0}, {0, 0}, {0, 1}, {1, 0}},
                    {{0, 1}, {0, 0}, {1, 0}, {0, -1}},
                    {{-1, 0}, {0, 0}, {0, -1}, {1, 0}},
                    {{-1, 0}, {0, 0}, {0, 1}, {0, -1}}
            },
            { // SquareShape has one
                    {{0, 0}, {1, 0}, {1, 1}, {0, 1}}
            },
            { // ReverseLShape has four
                    {{-1, 0}, {0, 0}, {1, 0}, {1, 1}},
                    {{0, 2}, {0, 1}, {0, 0}, {1, 0}},
                    {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}},
                    {{0, 2}, {1, 2}, {1, 1}, {1, 0}}
            },
            { // LShape has four
                    {{-1, 1}, {-1, 0}, {0, 0}, {1, 0}},
                    {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
                    {{-1, 1}, {0, 1}, {1, 1}, {1, 0}},
                    {{-1, 0}, {0, 0}, {0, 1}, {0, 2}}
            }
    };

    private int[][] coords;
    private Tetromino shape;
    private Point origin;
    private Color color;

    private Random r = new Random();

    public Shape() {
        this.coords = new int[4][2]; // Each shape has 4 tetrominoes with an x and y pos
        this.shape = Tetromino.NillShape; // Initialize all shapes as empty shapes
        this.origin = new Point(0, 0); // Initialize origin
        this.numConfigs = 1;
        this.color = Color.BLACK; // Default color but should never appear
    }

    /**
     Returns coords
     */
    public int[][] getCoords() {
        return this.coords;
    }

    public int getNumConfigs() {
        return this.numConfigs;
    }

    public void setCoords(int[][] newCoords) {
        this.coords = newCoords;
    }

    public static Color getColorOf(Tetromino t) {
        return Shape.colors[t.ordinal()];
    }

    /**
     Returns rotation position of this shape
     */
    public int[][] getConfig(int i) {
        return configs[shape.ordinal()][i];
    }

    /**
     Set the coordinates of this shape based on the Tetromino requested
     */
    public void setShape(Tetromino shape) {
        int pos = shape.ordinal(); // Position in enumeration

        coords = configs[pos][0];
        numConfigs = configs[pos].length;
        this.shape = shape;
        this.color = colors[pos];
    }

    public Tetromino getShape() {
        return this.shape;
    }

    public Point getOrigin() {
        return this.origin;
    }

    public void setOrigin(Point p) {
        this.origin = p;
    }

    public int getX(int i) {
        return this.coords[i][0]+origin.x;
    }

    public int getY(int i) {
        return this.coords[i][1]+origin.y;
    }

    public void setNewShape() {
        int s = r.nextInt(7)+1;

        Tetromino[] vals = Tetromino.values();
        this.setShape(vals[s]);
    }

    /**
     minX for left boundary restrictions
     */
    public int minX() {
        int m = coords[0][0]+origin.x;
        for (int i = 1; i < 4; i++) {
            m = Math.min(m, coords[i][0]+origin.x);
        }
        return m;
    }

    /**
     maxX for right boundary restrictions
     */
    public int maxX() {
        int m = coords[0][0]+origin.x;
        for (int i = 1; i < 4; i++) {
            m = Math.max(m, coords[i][0]+origin.x);
        }
        return m;
    }

    /**
     maxY for bottom boundary restrictions
     */
    public int maxY() {
        int m = coords[0][1]+origin.y;
        for (int i = 1; i < 4; i++) {
            m = Math.max(m, coords[i][1]+origin.y);
        }
        return m;
    }

    /**
     minY to determine end game state
     */
    public int minY() {
        int m = coords[0][1]+origin.y;
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][1]+origin.y);
        }
        return m;
    }


}