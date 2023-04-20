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

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        String fileName = args[0];
        String port = args[1];

        File cookieFile = new File(fileName);
        if (!cookieFile.exists()) {
            System.out.println("Cookie file not found");
            System.exit(0);
        }

        Cookie cookie = new Cookie();
        cookie.readCookieFile(fileName);
        String myCookie = cookie.getRandomCookie();
        System.out.println(myCookie);
        String myCookie2 = cookie.getRandomCookie();
        System.out.println(myCookie2);

        //slide 8 - establish server connection
        ServerSocket server = new ServerSocket(Integer.parseInt(port));
        Socket socket = server.accept();

        try(InputStream is = socket.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            String msgReceived = "";

            try(OutputStream os = socket.getOutputStream()) {
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(bos);

                while (!msgReceived.equals("close")){
                    msgReceived = dis.readUTF();

                    if (msgReceived.equals("get-cookie")) {
                        String randomCookie = cookie.getRandomCookie();

                        dos.writeUTF(randomCookie);
                    }
                }
                //closes all output streams in reverse order
                dos.close();
                bos.close();
                os.close();
            } catch (EOFException ex) {
                ex.printStackTrace();
            }

            //close all input streams in reverse order
            dis.close();
            bis.close();
            is.close();

        } catch (EOFException ex) {
            socket.close();
            server.close();
        }

    }
}
