package miniProjectSanta;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
	static Semaphore elfSem = new Semaphore(3);
	static Semaphore reinSem = new Semaphore(9);
	static Thread santaThread;
    static Thread elfProblem;
    static Thread ui;
    static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		// 1. all nine reindeer coming back from vacation
		// 2. at least 3 elves having probs
		
		santaThread = new Thread(new Runnable() { public void run() {			// creating santaThread, who is sleeping until its awaken
			System.out.println("Santa is sleeping\n");
			try {
				santaThread.sleep(Long.MAX_VALUE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}});
		santaThread.start();
		
		
		
//		ui = new Thread(new Runnable() {public void run() {
//			System.out.println("1. fill with elf problems");						// creating choices of what to do
//			System.out.println("2. fill with reindeers");
//			System.out.println("3. check for 3 elves and all the reindeers");
//			int choice = sc.nextInt();
//			switch(choice) {
//				case 1: 
//					elves();
//					break;
//				case 2: 
//					reindeers();
//					break;
//				case 3: 
//					wakingUpSanta();
//					break;
//			}
//			}});
//			ui.start();
		showUI();
	}
	
	public static void showUI() {
		System.out.println("1. fill with elf problems");						// creating choices of what to do
		System.out.println("2. fill with reindeers");
		System.out.println("3. check for 3 elves and all the reindeers");
		int choice = sc.nextInt();
		switch(choice) {
			case 1: 
				{
					elves();
					break;
				}
			case 2: 
				{
					reindeers();
					showUI();
					break;
				}
			case 3: 
				{
					wakingUpSanta();
					showUI();
					break;
				}
		}
	}
		
		//elves();
		//reindeers();
		//wakingUpSanta();
		//santaBackToSleep();
	
	
	public static void reindeers() {													// creating reindeers
		ExecutorService reinExecutor = Executors.newCachedThreadPool();
		for(int i = 0; i < reinSem.availablePermits(); i++) {
			try {
				reinSem.acquire();
				reinExecutor.execute( new Runnable() { public void run() {
					System.out.println("Hey Santa, im back from the vacation.");
				if (reinSem.availablePermits() == 1) {
					System.out.println("Im the last one, waking up Santa...");				// overriding santaThread, again...
					Thread santaThread = new Thread(new Runnable() {public void run() {
						System.out.println("Im awake boyz... Lets get to work");
					}});
					santaThread.start();
				}
				}});

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally {
				reinSem.release();
			}
		}
		reinExecutor.shutdown();
	}
	
	public static void elves() {														// creating elf problems
		ExecutorService elfExecutor = Executors.newCachedThreadPool();
		elfProblem = new Thread(new Runnable() {public void run() {						// creating threads for the elf problems
			System.out.println("Santa I have a problem.");		
			}});
		for ( int i = 0; i < elfSem.availablePermits(); i++) {
			try {
				elfSem.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				elfExecutor.execute(elfProblem);
			}
			finally {
				elfSem.release();
			}
		}
		elfExecutor.shutdown();
		santaBackToSleep();
		System.out.println("Finished and went outside santabacktosleep");
	}
	
	public static void santaBackToSleep() {
		santaThread = new Thread(new Runnable() {public void run() {					// overriding the original santaThread
			System.out.println("\nSanta: jobs done. Im gonna go back to sleep");
		}});
		santaThread.start();
		try {
			santaThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally { //after being done, goes back to sleep
			showUI();
			try {
				santaThread.sleep(Long.MAX_VALUE); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void wakingUpSanta() {												// overriding santaThread again, waking up santa, when 3 elves and all raindeers are back
		santaThread = new Thread(new Runnable() {public void run() {
		if (elfSem.availablePermits() == 0 && reinSem.availablePermits() == 0) {
			System.out.println("\nAll reindeers are back, and we have 3 elves with problems..");
			System.out.println("\nSanta: Oh I have company... Well, Im gonna go with the reindeers");
		}}});
		santaThread.start();
	}
}
	
