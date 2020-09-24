import java.lang.reflect.Array;

public interface Filter {
        boolean accept(String x);


        public static String[] filter(String[] a, Filter f) {
             String[] s = {}; // array to hold accepted strings
             int counter = 0; // keep position for where to add next accepted string
             for (int i = 0; i < a.length; i++){ // loop through possible strings
                 if (f.accept(a[i])) // see if filter accepts string
                {
                     s[counter] = a[i]; // add to s
                     counter++; // increment position of s
                }
            }
            return s; // return array with accepted strings
        }

    }