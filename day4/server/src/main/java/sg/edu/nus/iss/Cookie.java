package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {

    List<String> cookies = null;

    public void readCookieFile(String fileName) throws FileNotFoundException, IOException{
        //instantiate the cookies collection object
        cookies = new ArrayList<>();

        //use a file object to pass the filename
        File cookieFile = new File(fileName);

        // use file reader and buffered read for reading from cookie file
        //instantiate a filereader followed by a bufferedreader
        FileReader fr = new FileReader(cookieFile);
        BufferedReader br = new BufferedReader(fr);
        
        //while loop to loop through the file
        //read each line and add into the cookies collection object
        String fortuneCookie = "";
        while ((fortuneCookie = br.readLine()) != null) {
            cookies.add(fortuneCookie);
        }
        
        //close the buffered read and filereader in reverse order
        br.close();
        fr.close();
    }

    public String getRandomCookie() {
        Random random = new Random();

        if (cookies == null) {
            return "There are no more cookies :(";

        } else {
            String randomCookie = cookies.get(random.nextInt(cookies.size()));
            return randomCookie;
        }
        
    }
}
