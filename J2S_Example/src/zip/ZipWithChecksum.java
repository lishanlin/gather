package zip;

import java.io.*;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipWithChecksum {
    public static void main(String[] args) {
        try {
            String target = "data-1.zip";

            FileOutputStream fos = new FileOutputStream(target);

            //
            // An output stream that also maintains a checksum of the data being
            // written. The checksum can then be used to verify the integrity of
            // the output data.
            //
            CheckedOutputStream checksum = new CheckedOutputStream(fos, new Adler32());
            ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(checksum));

            int size = 0;
            byte[] buffer = new byte[1024];

            //
            // Get all text files on the working folder.
            //
            File dir = new File(".");
            String[] files = dir.list(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    if (name.endsWith(".txt")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });

            for (int i = 0; i < files.length; i++) {
                System.out.println("Compressing: " + files[i]);

                FileInputStream fis = new FileInputStream(files[i]);
                BufferedInputStream bis = new BufferedInputStream(fis, buffer.length);

                // put a new ZipEntry in the ZipOutputStream
                ZipEntry zipEntry = new ZipEntry(files[i]);
                zos.putNextEntry(zipEntry);

                // read data to the end of the source file and write it to the zip
                // output stream.
                while ((size = fis.read(buffer, 0, buffer.length)) > 0) {
                    zos.write(buffer, 0, size);
                }

                zos.closeEntry();
                fis.close();
            }

            // Finish zip process
            zos.close();

            //
            // Print out checksum value
            //
            System.out.println("Checksum   : " + checksum.getChecksum().getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
