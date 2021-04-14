import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Semaphore;

/**
 * 
 */

/**
 * @author KAJE
 *
 */
public class NioLineCount {
	
	static int theCount = 0;
	// A 0 means that you will be waited until signalled
	static Semaphore partialReadGate = new Semaphore(1);
	static boolean doContinue = true;
	static long readPosition = 0;

	private static int lineCount(String thePath, boolean doDance){
		if ( ! doDance ) return -1;
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Path myPath = Paths.get( thePath );
		try {
			AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(myPath, StandardOpenOption.READ);
			while ( doContinue ) {
				try {
					// In the end we will be waiting here
					partialReadGate.acquire();
					if ( ! doContinue ) return theCount;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fileChannel.read(buffer, readPosition, buffer, new CompletionHandler<Integer,ByteBuffer>() {
					@Override
					public void completed(Integer result, ByteBuffer attachment) {
						// System.out.println("Result = " + result + " Limit = " + attachment.limit());
						if ( result != -1 ) {
						attachment.flip();
						byte[] data = new byte[attachment.limit()];
						attachment.get( data );
						//System.out.println(new String(data));
						for (int i = 0; i < data.length; i++ ) {
							if ( data[i] == '\n' )
								theCount++;
							//System.out.println("data: " + data[i]);
						}
						// System.out.println("position: " + readPosition + " Count: " + theCount);
						readPosition += data.length;
						attachment.clear();
						} else {
							doContinue = false;
						}
						partialReadGate.release();
					}
				
					@Override
					public void failed(Throwable exc, ByteBuffer attachment) {
					}
				});
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theCount;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		long startTime = System.nanoTime();
		lineCount("", false);
		long dryRun = System.nanoTime() - startTime;
		startTime = System.nanoTime();
		int lines = lineCount("input.txt", true);
		long stopTime = System.nanoTime();
		System.out.println("Lines: " + String.valueOf(lines));
		System.out.println("Time it took: " + (stopTime - startTime - dryRun) + "  " + dryRun);
	}

}
