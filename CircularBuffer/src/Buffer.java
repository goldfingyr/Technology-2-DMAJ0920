
// All non primary objects (int, char etc) have wait, notify, notifyAll
// Methods "synchronized" to provide intrinsic locks
// If a thread calling wait() method does not own the inherent lock,
//   an error will be thrown.
public class Buffer {
	private int BufferSize = 4;
	private int[] Container = new int[BufferSize];
	private int PosR=0, PosW=0;
	
	public synchronized int Read() {
		while (true) {
			if ( PosR == PosW ) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				int v=Container[ PosR ];
				PosR = (PosR+1) % BufferSize;
				System.out.println("Read pos " + PosR);
				notifyAll();
				return v;
			}
		}
	}
	
	public synchronized void Write( int Val ) {
		while (true) {
			if (((PosW + 1) % BufferSize) == PosR) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Container[PosW] = Val;
				PosW = (PosW+1) % BufferSize;
				System.out.println("Write pos " + PosW);
				notifyAll();
				return;
			}
		}
	}
}
