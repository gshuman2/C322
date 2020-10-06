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

    


}