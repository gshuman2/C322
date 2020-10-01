/* Garrett N. Shuman
HW04
10/1/20
Used lecture video code where applicable

*/
public class User  extends Waarneembaar implements Waarneemer {
    Screen screen;
    String name;
    public String toString() {
        return this.name;
    }
    public User (String name){
      //  System.out.println("User " + name + " Has been created!");
        this.name = name;
        this.screen = new Screen(this);
    }
    public void update (Waarneembaar thing, Object message) {
        System.out.println(this.name + " Hears " + thing + " Saying: " + message);
        screen.chat.setText(screen.chat.getText() + " \n"  + thing + ": " + message); // append old text and receive new messages
    }
    public void broadcast(String message) {
        System.out.println(this.name + " Says: " + message);
        setChanged();
        notifyObservers(message);
        clearChanged();
    }
}
