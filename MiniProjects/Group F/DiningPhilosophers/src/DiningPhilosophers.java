public class DiningPhilosophers {

    public static void main(String[] args) throws Exception {

        final Philosopher[] philosophers = new Philosopher[5];
        Object[] chops = new Object[philosophers.length];

        for (int i = 0; i < chops.length; i++) {
            chops[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftChop = chops[i];
            Object rightChop = chops[(i + 1) % chops.length];

            if (i == philosophers.length - 1) {
                
                // The last philosopher picks up the right chop first
                philosophers[i] = new Philosopher(rightChop, leftChop); 
            } else {
                philosophers[i] = new Philosopher(leftChop, rightChop);
            }
            
            Thread t 
              = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }
}