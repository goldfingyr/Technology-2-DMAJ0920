
public class CircularBuffer {
	
	static Buffer MyBuffer = new Buffer();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Thread(() -> {
		    System.out.println("Producer running");
		    for ( int nn=0; nn < 20; nn++) {
		    	MyBuffer.Write( nn );
		    	System.out.println("Producer Wrote " + nn);
		    }
		}).start();
		new Thread(() -> {
			System.out.println("Consumer running");
			for ( int nn=0; nn < 20; nn++ ) {
				System.out.println("Consumer Read " + MyBuffer.Read());
			}
		}).start();
		
	}

}
