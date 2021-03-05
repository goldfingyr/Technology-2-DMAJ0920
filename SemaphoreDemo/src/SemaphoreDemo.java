// java program to demonstrate 
// use of semaphores Locks 
import java.util.concurrent.*; 


// Driver class 
public class SemaphoreDemo 
{ 
	public static void main(String args[]) throws InterruptedException 
	{ 
		// creating a Semaphore object 
		// A 1 means that you are allowed 1 write to start with
		Semaphore semPro = new Semaphore(1);
		// A 0 means that you are not allowed to read (as nobody has yet written to the buffer)
		Semaphore semCon = new Semaphore(0);
		
		// creating two threads with name A and B 
		// Note that thread A will increment the count 
		// and thread B will decrement the count 
		MyThread mt1 = new MyThread(semPro, semCon, "Producer"); 
		MyThread mt2 = new MyThread(semPro, semCon, "Consumer"); 
		
		// stating threads A and B 
		mt1.start(); 
		mt2.start(); 
		
		// waiting for threads A and B 
		mt1.join(); 
		mt2.join(); 
		
		// count will always remain 0 after 
		// both threads will complete their execution 
		System.out.println("count: " + Shared.count); 
	} 
} 
