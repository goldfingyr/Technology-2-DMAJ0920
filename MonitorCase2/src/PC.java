/*
 *  In MonitorCase1, the producer thread quickly fills the buffer 
 *  with characters and then waits for the consumer to consume 
 *  some characters from the buffer. The problem is that the 
 *  producer waits inside the monitor associated with the buffer, 
 *  preventing the consumer to execute the synchronized Get 
 *  method on the buffer. 
 *  
 *  We really want the Producer to release the monitor if the 
 *  buffer becomes full and allow the Consumer to proceed. 
 *  Similarly, the Consumer must release the monitor if the 
 *  buffer becomes empty and allow the Producer to proceed. 
 *  To coordinate the two threads, we must use the Object's 
 *  wait() and notify or notifyAll() methods. 
 *  
 *  The wait() method suspends the calling thread and temporarily 
 *  releases ownership of the monitor (so it allows other threads 
 *  to acquire the monitor). The suspended thread that called 
 *  wait() wakes up only when another thread calls notify() or 
 *  notifyAll() on that object. 
 *  
 *  The notifyAll() method wakes up all threads waiting on the 
 *  object in question. The awakened threads compete for the 
 *  monitor. One thread gets it, and the others go back to 
 *  waiting. 

 *  
 *  Code courtesy of www.csc.villanova.edu/~mdamian/threads/javamonitors.html
 */
public class PC {

	public static void main(String[] args) {
		Buffer b = new Buffer(4);
		Producer p = new Producer(b);
		Consumer c = new Consumer(b);

		p.start();
		c.start();
		
		try {
			p.join();
			c.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End...");
	}

}
