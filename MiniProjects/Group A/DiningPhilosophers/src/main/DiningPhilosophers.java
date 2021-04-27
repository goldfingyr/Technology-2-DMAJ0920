package main;

public class DiningPhilosophers {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		run();
		
	}
	
	public static void run() {
		
		//creates the philosophers and the chopsticks
		Philosophers[] philosophers = new Philosophers[5];
		Object[] chopsticks = new Object[5];
		
		for(int i = 0; i < chopsticks.length; i++) {
			chopsticks[i] = new Object();
		}
		
		for(int i = 0; i< philosophers.length; i++) {
			Object leftChopstick = chopsticks[i];
			Object rightChopstick = chopsticks[(i+1) % chopsticks.length];
			
			//declares that each philosopher picks up first the left chopstick except the last one
			//who picks up the right one preventing circular wait, so the code is deadlock free
			if(i<philosophers.length-1) philosophers[i] = new Philosophers(leftChopstick, rightChopstick);
			else philosophers[i] = new Philosophers(rightChopstick, leftChopstick);
			
			//starts the thread
			Thread t = new Thread(philosophers[i], "Philosopher " + (i+1));
			t.start();
		}
		
		
	}

}
