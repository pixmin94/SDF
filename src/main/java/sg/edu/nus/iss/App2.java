package sg.edu.nus.iss;

import java.io.File;
import java.io.IOException;

public class App2 {
    public static void main( String[] args ) throws IOException
    {
        String dirName = args[0];
        File newDirectory = new File(dirName);

        if (newDirectory.exists()) {
            System.out.println("Accessing "+dirName+" directory..");
        } else {
            newDirectory.mkdir();
            System.out.println("New directory "+dirName+" created!");
        }
        
    }
}
