package sg.edu.nus.iss;

import java.io.*;
import java.util.*;
import java.io.EOFException;
import java.net.Socket;

public class CookieClientHandler implements Runnable {
    
    private final Socket socket;

    public CookieClientHandler(Socket s) {
        socket = s;
    }

    @Override
    public void run() {
        Cookie cookie = new Cookie();

        try(InputStream is = socket.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            String msgReceived = "";

            try(OutputStream os = socket.getOutputStream()) {
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(bos);

                while (!msgReceived.equals("close")){
                    msgReceived = dis.readUTF();
                    System.out.println(msgReceived);

                    if (msgReceived.equals("get-cookie")) {
                        String randomCookie = cookie.getRandomCookie();

                        dos.writeUTF(randomCookie);
                        dos.flush();
                    } else {
                        dos.writeUTF("");
                        dos.flush();
                    }
                }
                dos.close();
                bos.close();
                os.close();

            } catch (EOFException ex) {
                ex.printStackTrace();
            }
            dis.close();
            bis.close();
            is.close();

        } catch (IOException ex) {
            System.err.printf("Error; %s\n", ex.getMessage());
            ex.printStackTrace();
        } finally {
            try { socket.close(); } catch (Exception e) { }
            // server.close();
        }
    }
    
}
