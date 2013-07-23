package jarTest;
import java.io.IOException;
import java.util.jar.JarFile;

/**
 * jar文件处理
 * @author 李山林
 *
 */
public class Test {
	public static void main(String[] args) throws IOException {
		
	}
	
	public void testManifest() throws IOException{
		JarFile jar = new JarFile("D:\\Workspaces58\\Test\\lib\\mongo-2.7.2.jar");
		jar.getManifest().write(System.out);
	}
}
