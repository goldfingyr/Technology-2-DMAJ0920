import java.util.ArrayList;


public class DiningPhilosophers {



    public static void main(String[] args) {

        ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
        ArrayList<Fork> forks = new ArrayList<Fork>();
        Queue queue = new Queue();
        int philosophersQuantity = 5;


        for(int i=0; i<philosophersQuantity; i++) {
            forks.add(new Fork());
        }

        for(int i=0; i<philosophersQuantity; i++) {
            philosophers.add(new Philosopher(forks.get(i), forks.get((i + 1) % philosophersQuantity), queue, i));
        }

        for(int i=0; i<philosophersQuantity; i++) {
            Thread thread = new Thread(philosophers.get(i), "philosopher " + (i+1));
            thread.start();
        }
    }
}
