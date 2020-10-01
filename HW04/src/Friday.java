/* Garrett N. Shuman
HW04
10/1/20
Used lecture video code where applicable

*/
public class Friday {
    public static void main(String[] args) {
        User a = new User("Adrian");
        User b = new User("Rebekah");
        User c = new User("Ari");
        a.addObserver(b);
        a.addObserver(c);
        b.addObserver(a);
        b.addObserver(c);
        c.addObserver(a);
        c.addObserver(b);
        Screen x = new Screen(a);
        Screen y = new Screen(b);
        Screen z = new Screen(c);
    }
}