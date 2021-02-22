
public class ThreadsExtend extends Thread {
	
	private String MyString;

	public ThreadsExtend( String InitString ) {
		MyString = InitString;
	}
	
	public void run() {
		System.out.println("Thread running: " + MyString);
	}
	
}
