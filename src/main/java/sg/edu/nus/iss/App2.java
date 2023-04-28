package sg.edu.nus.iss;

import java.io.Console;
import java.io.File;
import java.io.IOException;

public class App2 {
    public static void main( String[] args ) throws IOException
    {
        String dirName = args[0];
        File newDirectory = new File(dirName);

        if (args.length == 0) {
            dirName = "db";
            
        } else if (newDirectory.exists()) {
            System.out.println("Accessing "+dirName+" directory..");
            
        } else {
            newDirectory.mkdir();
            System.out.println("New directory "+dirName+" created!");
        }

        ShoppingCartDB directory = new ShoppingCartDB(dirName);
        String input = "";

        while (!input.equals("quit")){
            Console con = System.console();
            input = con.readLine("Please login: ");
            directory.login(input);

        }
        
    }
}
