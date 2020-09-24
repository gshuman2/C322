import javax.swing.*;

/**
 A class for testing a DrawableIcon
 */
public class DrawableTester
{
    /**
     Creates a JOptionPane that contains a DrawableIcon
     @param args unused
     */
    public static void main(String[] args)
    {
        JOptionPane.showMessageDialog(
                null,
                "Hello, Car!",
                "Message",
                JOptionPane.INFORMATION_MESSAGE,
                new DrawableIcon(new Car(0, 0, 100), 100, 50));
        System.exit(0);
    }
}
