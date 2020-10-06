import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
/*
import tetris.World;
import tetris.Shape;
import tetris.Shape.Tetromino;
import java.util.Arrays;
*/

/**
 The goal of this class is to implement the World interface and provide suitable functions to
 run Tetris appropriately.
 */

public class Tetris implements World {
    /**
     Our board representation will be a two-dimensional array of tetrominos
     */
    private int BOARD_WIDTH;
    private int BOARD_HEIGHT;
    private Shape.Tetromino[][] board;
    private Shape currPiece;
    private int config;
    private int tallest;
    private int score;

    public Tetris(int width, int height) {
        this.BOARD_HEIGHT = height;
        this.BOARD_WIDTH = width;
        this.board = new Shape.Tetromino[BOARD_HEIGHT][BOARD_WIDTH];
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j =0; j < BOARD_WIDTH; j++) {
                this.board[i][j] = Shape.Tetromino.NillShape;
            }
        }

        currPiece = new Shape();
        currPiece.setOrigin(new Point(5, 0));

        currPiece.setNewShape();
        config = 0;
        tallest = BOARD_HEIGHT;
        score = 0;

    }

    /**
     Return true if a block of the currPice is colliding with another on the board
     */
    public boolean colliding() {
        Point p = currPiece.getOrigin();

        if (currPiece.minX() < 0 || currPiece.maxX() >= BOARD_WIDTH ||
                currPiece.maxY() < 0 || currPiece.maxY() >= BOARD_HEIGHT || currPiece.minY() < 0) {

            return true;
        }

        for (int i = 0; i < 4; i++) {
            int x = currPiece.getX(i);
            int y = currPiece.getY(i);

            if (board[y][x] != Shape.Tetromino.NillShape) {
                return true;
            }
        }
        return false;
    }

    /**
     Rotates the currPiece to the right.
     */
    public void rotateR() {
        config++;

        if (config > currPiece.getNumConfigs() - 1) {
            config = 0;
        }
        int[][] newCoords = currPiece.getConfig(config);
        currPiece.setCoords(newCoords);
        // System.out.println(Arrays.deepToString(currPiece.getCoords()));
    }

    public void rotateL() {
        config--;
        if (config < 0) {
            config = currPiece.getNumConfigs() - 1;
        }
        int[][] newCoords = currPiece.getConfig(config);
        currPiece.setCoords(newCoords);
    }

    public void translate(int dx, int dy) {
        Point p = currPiece.getOrigin();
        int x = p.x + dx;
        int y = p.y + dy;

        currPiece.setOrigin(new Point(x, y));
    }


    public void onTick(ActionEvent e) {
        this.translate(0, 1);

        if (colliding()) {
            this.translate(0, -1);
            this.genNewPiece();
            this.tryToBreak();
        }

    }

    public void genNewPiece() {
        this.tallest = Math.min(tallest, currPiece.minY());

        for (int i = 0; i < 4; i++) {
            int y = currPiece.getY(i);
            int x = currPiece.getX(i);

            this.board[y][x] = currPiece.getShape();
        }
        this.currPiece = new Shape();

        currPiece.setNewShape();

        currPiece.setOrigin(new Point(5, 0));

        config = 0;
    }

    public void onKey(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                this.translate(1, 0);
                if (colliding()) {
                    this.translate(-1, 0);
                }
                break;
            case KeyEvent.VK_LEFT:
                this.translate(-1, 0);
                if (colliding()) {
                    this.translate(1, 0);
                }
                break;
            case KeyEvent.VK_DOWN:
                this.translate(0, 1);
                if (colliding()) {
                    this.translate(0, -1);
                }
                break;
            case KeyEvent.VK_SPACE:
                this.rotateR();
                if (colliding()) {
                    this.rotateL();
                }
                break;

        }
    }


    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.drawString("" + score, 0, 10);

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (this.board[i][j] != Shape.Tetromino.NillShape) {
                    g2.setColor(Color.BLACK);
                    g2.fillRect(j*10, i*10, 10, 10);
                    g2.setColor(Shape.getColorOf(this.board[i][j]));
                    g2.fillRect(j*10+1, i*10+1, 9, 9);
                }
            }
        }

        // Draw each square
        for (int i = 0; i < 4; i++) {

            int x = currPiece.getX(i);
            int y = currPiece.getY(i);
            Point p = currPiece.getOrigin();
            g2.setColor(Color.BLACK);

            g2.fillRect(x*10, y*10, 10, 10);
            g2.setColor(Shape.getColorOf(this.currPiece.getShape()));
            g2.fillRect(x*10 + 1, y*10 + 1, 9, 9);
        }
        if (terminated()) {
            g2.setColor(Color.BLACK);
            g2.drawString("Score: " + score, 0, BOARD_HEIGHT * 5);
        }
    }

    /**
     Returns true if the the given line is full
     */
    public boolean isFullLine(Shape.Tetromino[] row) {

        for (Shape.Tetromino t : row) {
            if (t == Shape.Tetromino.NillShape) {
                return false;
            }
        }
        return true;
    }

    /**
     Utility function for setting a current row to its previous row
     */
    public void setRowToPrev(int row) {
        if (row == 0) {
            return;
        }
        tallest++;
        for (int i =0; i < BOARD_WIDTH; i++) {
            board[row][i] = board[row-1][i];
        }
    }

    /**
     Tries to break as many lines as possible. Calls itself to start over after breaking a line.
     */
    public void tryToBreak() {
        for (int i = BOARD_HEIGHT - 1; i >= tallest; i--) {

            if (isFullLine(board[i])) {
                for (int j = i; j >= tallest; j--) {
                    setRowToPrev(j);
                }
                tryToBreak();
                tallest--;

                score++;
            }
        }
    }
    public boolean terminated() {return tallest <= 1;}
    public void exitMessage() {} // No exit message. Draw will display it when terminated

    public static void main(String[] args) {

        Tetris t = new Tetris(15, 30);
        BigBang bb = new BigBang(t);
        bb.start(500, 150, 300);

    }


}