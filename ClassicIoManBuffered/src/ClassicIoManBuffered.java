import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author KAJE
 *
 */

public class ClassicIoManBuffered {

	private static int lineCount(String path, boolean doDance){
		if ( ! doDance ) return -1;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream( path );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte buf[] = new byte[2048];
		int cnt = 0;
		int n;
		try {
			while ((n = fis.read(buf)) != -1) {
				for (int i = 0; i < n; i++) {
					if (buf[i] == '\n') cnt++; 
				}
			}
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
