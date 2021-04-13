import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 
 */

/**
 * @author KAJE
 *
 */
public class NioLineCount {

	private static int lineCount(String thePath, boolean doDance){
		if ( ! doDance ) return -1;
		ByteBuffer buffer = java.nio.ByteBuffer.allocate(1024);
		Path myPath = Paths.get( thePath );
		try {
			AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(myPath, StandardOpenOption.READ);
			fileChannel.read(buffer, 0, buffer, new CompletionHandler<Integer,ByteBuffer>() {
				@Override
				public void completed(Integer result, ByteBuffer attachment) {
					System.out.println("Result = " + result);
					attachment.flip();
					byte[] data = new byte[attachment.limit()];
					attachment.clear();
				}
			
				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
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
