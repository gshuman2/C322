/* Garrett N. Shuman
HW04
10/1/20
Used lecture video code where applicable

*/
public class Chat {
    public static void main(String[] args) {
        User a = new User("Leslie");
        User b = new User("Alex");
        User c = new User("Chris");
        a.addObserver(b);
        a.addObserver(c);
        b.addObserver(a);
        b.addObserver(c);
        c.addObserver(a);
        c.addObserver(b);
        //c.broadcast("Hello World!"); // Chris
        //a.broadcast("Hi, Chris!"); //Leslie Responds
    }
}

