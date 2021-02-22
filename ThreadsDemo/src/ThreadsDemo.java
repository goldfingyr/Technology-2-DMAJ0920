
public class ThreadsDemo {

	public static void main(String[] args) {
		ThreadsExtend myThreadA = new ThreadsExtend("A");
		ThreadsExtend myThreadB = new ThreadsExtend("B");
		ThreadsExtend myThreadC = new ThreadsExtend("C");
		Runnable myRunnableD = new ThreadsInterface("D");
		Runnable myRunnableE = new ThreadsInterface("E");
		myThreadA.start();
		myThreadB.start();
		myThreadC.start();
		Thread myThreadD = new Thread( myRunnableD );
		myThreadD.start();
		Thread myThreadE = new Thread( myRunnableE );
		myThreadE.start();
		try {
			myThreadA.join();
			myThreadB.join();
			myThreadC.join();
			myThreadD.join();
			myThreadE.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done...");
	}

}
