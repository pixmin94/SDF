package day7;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Client {

    public static void main(String[] args) throws IOException {

        String filePath = args[0];
        File file = new File(filePath);
        long fileSize = file.length();
        System.out.println("Sending file of size: "+fileSize+" bytes.");

        Socket socket = new Socket("localhost", 3000);

        try {
            InputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            OutputStream os = socket.getOutputStream();
            byte[] buffer = new byte[4 * 1024];
            
            int size;
            while ((size = bis.read(buffer)) > 0) {
                os.write(buffer, 0, size);
                os.flush();
            }
        } finally {
            os.close();
            socket.close();
        }
    }

}