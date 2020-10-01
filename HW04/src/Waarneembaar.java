/* Garrett N. Shuman
HW04
10/1/20
Used lecture video code where applicable

*/
import java.util.ArrayList;
import java.util.*;

public class Waarneembaar {
   ArrayList<Waarneemer> observers = new ArrayList<Waarneemer>();
   public void addObserver(Waarneemer observer) {
       this.observers.add(observer);
   }
   public void setChanged() { }
   public void clearChanged() {}
   public void notifyObservers (Object message) {
       for (Waarneemer observer : this.observers)
           observer.update (this, message);

   }
}
