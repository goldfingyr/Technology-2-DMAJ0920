
public class Queue {
    private Philosopher philosopherWhoFinishedEating = null;
    private int eatingRoot = 0;

    public boolean canPhilosopherEat(Philosopher philosopher) {
        if((philosopher.getIndex() == eatingRoot && philosopherWhoFinishedEating != philosopher) || (philosopher.getIndex() == (eatingRoot + 2)%5 &&  philosopherWhoFinishedEating != philosopher)) {

            return true;
        }
        else {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public void eatingFinished(Philosopher philosopher) {
        if (philosopherWhoFinishedEating ==  null)
            philosopherWhoFinishedEating = philosopher;
        else if (philosopherWhoFinishedEating != null) {
            eatingRoot = (eatingRoot + 1)%5;
            philosopherWhoFinishedEating = null;
            if(eatingRoot >= 3)
                System.out.println("\nNow philosopher " + (eatingRoot + 1) + " and " + (((eatingRoot + 3)%6)+1) + " can eat");
            else
                System.out.println("\nNow philosopher " + (eatingRoot + 1) + " and " + (eatingRoot + 3)%6 + " can eat");
        }
    }
}
