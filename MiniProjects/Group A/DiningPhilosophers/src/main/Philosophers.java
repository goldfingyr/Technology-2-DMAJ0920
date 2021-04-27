package main;

public class Philosophers implements Runnable {
	
	private Object leftChopstick;
	private Object rightChopstick;
	
	//A philosopher has two attributes which represents the two forks
	public Philosophers(Object leftChopstick, Object rightChopstick) {
		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;
	}
	
	//synchronized keyword does not allow other threads into the block while another thread exists in the block
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			while (true) {
				print(System.nanoTime() + ": Thinking");
				
				synchronized (leftChopstick) {
					print(System.nanoTime() + ": Picked up left chopstick");
					
					synchronized (rightChopstick) {
						print(System.nanoTime() + ": Picked up rigth chopstick - Philosopher " + Thread.currentThread().getName() + " is eating");
						print(System.nanoTime() + ": Put down right chopstick");
					}
					
					print(System.nanoTime() + ": Put down left chopstick - Philosopher " + Thread.currentThread().getName() + " is thinking");
				}
				
				
			}
		} catch(InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
		
	}
	
	//Prints out a thread associated with an action such as thinking, eating, putting a chopstick down or picking it up
	//Pauses the thread for a random amount of time
	public void print(String action) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " " + action);
		Thread.sleep((int) Math.random()*100);
	}
	
}
