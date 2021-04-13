import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author KAJE
 *
 */
public class ClassicIoUnbuffered {

	private static int lineCount(String path, boolean doDance){
		if ( ! doDance ) return -1;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		int cnt = 0;
		int b;
		try {
			while ((b = fis.read()) != -1) {
				if (b == '\n') cnt++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	public static void main(String[] args){
		long startTime = System.nanoTime();
		lineCount("", false);
		long dryRun = System.nanoTime() - startTime;
		startTime = System.nanoTime();
		int lines = lineCount("input.txt", true);
		long stopTime = System.nanoTime();
		System.out.println("Lines: " + String.valueOf(lines));
		System.out.println("Time it took: " + (stopTime - startTime - dryRun) + "  ");
	}
	
}
