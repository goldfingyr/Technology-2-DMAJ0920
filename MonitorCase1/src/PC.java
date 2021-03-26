/*
 *  Run this code.
 *  1: What happens ?
 *  2: Why does it happen ?
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
		System.out.println("End");
	}

}
