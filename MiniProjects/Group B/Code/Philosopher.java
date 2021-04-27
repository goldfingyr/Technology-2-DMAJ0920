import java.lang.Math;


public class Philosopher implements Runnable{

    Fork leftFork = null;
    Fork rightFork = null;
    Queue queue = null;
    Integer index;

    public Philosopher(Fork leftFork, Fork rightFork, Queue queue,int index) {
        this.index = index;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.queue = queue;
    }

    public Integer getIndex() {
        return index;
    }

    private void haltPhilosopher(double duration) {
        try {
            Thread.sleep((long)(duration * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat() {
        int duration = (int)(Math.random() * 4) + 1;
        String message = Thread.currentThread().getName() + " has started eating and will continue for " + duration + " seconds";
        System.out.println(message);
        haltPhilosopher(duration);
    }

    public void run() {

        while(true) {
            if(queue.canPhilosopherEat(this)) {
                synchronized (leftFork) {
                    System.out.println(Thread.currentThread().getName() + " has picked the left fork up");
                    synchronized (rightFork) {
                        System.out.println(Thread.currentThread().getName() + " has picked the right fork up");
                        eat();
                    }
                    System.out.println(Thread.currentThread().getName() + " has put the right fork down ");
                }

                System.out.println(Thread.currentThread().getName() + " has put the left fork down");
                System.out.println(Thread.currentThread().getName() + " has started thinking");
                queue.eatingFinished(this);
            }
        }
    }
}
