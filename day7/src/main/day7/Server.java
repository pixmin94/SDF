package day7;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {

        int port = 3000;
        // if (args.length > 0)
        //     port = Integer.parseInt(args[0]);

        System.out.printf("Starting server on port %d\n", port);

        ServerSocket server = new ServerSocket(port);

        Socket sock = server.accept();

        System.out.printf("Got a connection: %s\n", sock);

        InputStream is = sock.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);

        byte[] buffer = bis.readAllBytes();
        File file = new File("received_file.txt");

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(buffer);
        long fileSize = file.length();

        System.out.println("File size is: "+ fileSize +" bytes.");

        System.out.printf("Closing connection and exit");
        fos.close();
        sock.close();
        server.close();
        System.exit(0);
        
    }

}