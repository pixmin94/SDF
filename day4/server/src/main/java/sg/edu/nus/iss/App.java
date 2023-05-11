package sg.edu.nus.iss;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import sg.edu.nus.iss.CookieClientHandler;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        String fileName = args[0];
        String port = args[1];

        // instantiate file object
        File cookieFile = new File(fileName);
        if (!cookieFile.exists()) {
            System.out.println("Cookie file not found");
            System.exit(0);
        }

        // test cookie
        // Cookie cookie = new Cookie();
        // cookie.readCookieFile(fileName);
        // String myCookie = cookie.getRandomCookie();
        // System.out.println(myCookie);


        ServerSocket server = new ServerSocket(Integer.parseInt(port));
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        String input;

        while (true) {
            System.out.println("Waiting for connection...");
            Socket socket = server.accept();
            System.out.println("Connection received: "+socket);
            CookieClientHandler handler = new CookieClientHandler(socket);
            System.out.println("Dispatching client to thread pool");
            threadPool.submit(handler);

        }
        
    }
}
