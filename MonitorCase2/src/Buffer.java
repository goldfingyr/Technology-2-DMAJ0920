
public class Buffer {
	private char[] buffer;
	private int count = 0, in = 0, out = 0;

	Buffer(int size) {
		buffer = new char[size];
	}

	public synchronized void Put(char c) {
		while (count == buffer.length) {
			try {
				wait();
			} catch (InterruptedException e) {
			} finally {
			}
		}
		System.out.println("Producing " + c + " ...");
		buffer[in] = c;
		in = (in + 1) % buffer.length;
		count++;
		notify();
	}

	public synchronized char Get() {
		while (count == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			} finally {
			}
		}
		char c = buffer[out];
		out = (out + 1) % buffer.length;
		count--;
		System.out.println("Consuming " + c + " ...");
		notify();
		return c;
	}
}
