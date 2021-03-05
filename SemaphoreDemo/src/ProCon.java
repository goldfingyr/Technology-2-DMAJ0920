// java program to demonstrate 
// use of semaphores Locks 
import java.util.concurrent.*; 

//A shared resource/class. 
class Shared 
{ 
	static int count = 0; 
} 

class MyThread extends Thread 
{
	// We need 2 semaphores.
	// This one will prevent the Producer writing twice
	Semaphore semPro;
	// This one will prevent the consumer reading twice
	Semaphore semCon;
	String threadName;
	
	public MyThread(Semaphore mySemPro, Semaphore mySemCon, String threadName) 
	{ 
		super(threadName);
		// We set the semaphores from the main method
		this.semPro = mySemPro; 
		this.semCon = mySemCon;
		this.threadName = threadName;
	}
	
	@Override
	public void run()
	{ 
		
		// run by thread "Producer" 
		if(this.getName().equals("Producer")) 
		{
			System.out.println("Starting " + threadName);
			for ( int i=0; i<5; i++)
			{
				try
				{
					// Wait until we may write (initially we are allowed)
					// coming back here - we must wait for the "Consumer" to read
					semPro.acquire();
					Shared.count++; 
					System.out.println(threadName + " Writes: " + Shared.count); 
					// Signal the "Consumer" that a value is ready
					semCon.release();
					// Now, allowing a context switch -- if possible. 
					// for thread Consumer to execute 
					Thread.sleep(10); 

				}
				catch (InterruptedException exc)
				{
					// Should anything fail - then  we end up here
					System.out.println(exc); 
				} 
			}
			// And we are done 
			System.out.println("Ending " + threadName); 
		} 
		
		// run by thread Consumer 
		else
		{ 
			System.out.println("Starting " + threadName); 
			for (int i=0; i< 5; i++)
			{
				try
				{
					// Waiting for a value to be present (initially: Wait)
					semCon.acquire();
					System.out.println(threadName + " Reads: " + Shared.count);
					// Signal the "Producer" that the value was read, so the buffer is now cleared
					semPro.release();
				}
				catch (InterruptedException exc)
				{
					// Should we fail - here we go
					System.out.println(exc); 
				} 
			}
			// And we are done 
			System.out.println("Ending " + threadName);
		} 
	} 
} 

