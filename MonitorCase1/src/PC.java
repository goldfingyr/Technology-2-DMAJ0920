
public class PC {

	public static void main(String[] args) {
		Buffer b = new Buffer( 4 );
		Producer p = new Producer( b );
		Consumer c = new Consumer( b );
		
		p.start();
		c.start();
		
		try {
			p.join();
			c.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End");
	}

}
