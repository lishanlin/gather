package zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFileUnzipDemo {
    public static void main(String[] args) {
        String zipname = "data.zip";

        try {
            ZipFile zipFile = new ZipFile(zipname);
            Enumeration enumeration = zipFile.entries();

            while (enumeration.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) enumeration.nextElement();
                System.out.println("Unzipping: " + zipEntry.getName());

                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(zipEntry));

                int size;
                byte[] buffer = new byte[2048];

                FileOutputStream fos = new FileOutputStream(zipEntry.getName());
                BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);

                while ((size = bis.read(buffer, 0, buffer.length)) != -1) {
                    bos.write(buffer, 0, size);
                }

                bos.flush();
                bos.close();
                fos.close();

                bis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
