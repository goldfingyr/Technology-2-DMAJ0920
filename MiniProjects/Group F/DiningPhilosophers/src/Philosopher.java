public class Philosopher implements Runnable {
	
	private Object leftChop;
	private Object rightChop;
	
	public Philosopher(Object leftChop, Object rightChop) {
		this.leftChop = leftChop;
        this.rightChop = rightChop;
    }

    @Override
    public void run() {
    	try {
            while (true) {
                
                // thinking
                doAction(System.nanoTime() + ": Thinking");
                synchronized (leftChop) {
                    doAction(
                      System.nanoTime() 
                        + ": Picked up left chop");
                    synchronized (rightChop) {
                        // eating
                        doAction(
                          System.nanoTime() 
                            + ": Picked up right chop - eating"); 
                        
                        doAction(
                          System.nanoTime() 
                            + ": Put down right chop");
                    }
                    
                    // Back to thinking
                    doAction(
                      System.nanoTime() 
                        + ": Put down left chop. Back to thinking");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
    
    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }
}