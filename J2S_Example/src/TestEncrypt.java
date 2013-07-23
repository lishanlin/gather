import java.io.*;
import java.nio.charset.Charset;

class TestEncrypt {
    public static void main(String[] args) {
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("file.encoding=" + System.getProperty("file.encoding"));
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("Default Charset in Use=" + getDefaultCharSet());
        encrypt();
    }

    private static String getDefaultCharSet() {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        String enc = writer.getEncoding();
        return enc;
    }

    private static void   encrypt(){
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(new File("e.txt")));
            output.write(new DesUtils().encrypt("你好！"));
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
