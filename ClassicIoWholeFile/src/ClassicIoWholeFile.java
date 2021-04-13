import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 */

/**
 * @author KAJE
 *
 */
public class ClassicIoWholeFile {

	private static int lineCount(String path, boolean doDance){
		if ( ! doDance ) return -1;
		int len = (int)(new File(path).length());
		int cnt = 0;
		FileInputStream fis;
		try {
			fis = new FileInputStream( path );
			byte buf[] = new byte[len];
			try {
				fis.read(buf);
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < len; i++) {
				if (buf[i] == '\n') cnt++;
			}
		} catch (FileNotFoundException e) {
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
