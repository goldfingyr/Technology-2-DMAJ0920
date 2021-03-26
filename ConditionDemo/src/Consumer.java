// Consuming the words from the queue.
// Counting total number of words and keeping track of unique words
//
import java.util.HashSet;
import java.util.Set;
 
public class Consumer extends Thread {
    private Set knownObjects = new HashSet();
    private int total = 0;
    private int unique = 0;
    private SharedFiFoQueue queue;
 
    public Consumer(SharedFiFoQueue queue) {
        this.queue = queue;
    }
     
    @Override
    public void run() {
        try {
            do {
                Object obj = queue.remove();
                if(obj == null)
                    break;
                total++;
                 
                if(!knownObjects.contains(obj)) {
                    unique++;
                    knownObjects.add(obj);
                }
                 
                System.out.println("[Consumer] Read the element: " + obj.toString());
                 
            } while(true);
        }
        catch (InterruptedException ex) {
            System.err.println("An InterruptedException was caught: " + ex.getMessage());
            ex.printStackTrace();
        }
         
        System.out.println("\n[Consumer] " + total + " words read counting " + unique + " unique words");
    }
}