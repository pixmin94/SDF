package day7;

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

        Socket socket = new Socket("localhost", 3000);

        InputStream fis = new FileInputStream(file);
        OutputStream os = socket.getOutputStream();
        byte[] bytes = new byte[1024];
        
        int count;
        while ((count = fis.read(bytes)) > 0) {
            os.write(bytes, 0, count);
        }

        os.close();
        socket.close();
    }

}