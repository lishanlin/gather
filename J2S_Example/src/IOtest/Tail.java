package IOtest;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 * @author 李山林 模拟linux tail -f 命令 ，用RandomAccessFile实现
 */
public class Tail {
	public static void main(String[] args) {
		FileWriter writer = null;
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile("/opt/scf/log/infocheck.log", "r");
			writer = new FileWriter("/opt/scf/log/infocheck.log.my", true);
			long len = rf.length();
			long start = rf.length();
			rf.seek(len);
			String line = null;
			while (true) {
				line = rf.readLine();
				writer.write(line);

				len = rf.length();
				start = rf.getFilePointer();
				if (start == len)
					Thread.sleep(1000);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
