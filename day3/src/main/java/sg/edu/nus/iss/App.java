package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) throws IOException
    {
        // first argument passed in is the directory to create
        String dirPath = args[0];

        // Use File class to check if directory exists
        // if directory doesn't exist, create the directory using variable dirPath
        File newDirectory = new File(dirPath);
        if (newDirectory.exists()) {
            System.out.println("Accessing " + newDirectory + " directory");
        } else {
            newDirectory.mkdir();
        }

        System.out.println("Welcome to my Shopping Cart");

        // List collection to store the cart items of login user
        List<String> cartItems = new ArrayList<String>();

        // use Console class to read from keyboard input
        Console con = System.console();
        String input = "";

        // used to keep track of current login-in user
        // this is also used as the filename to store the user cart items
        String loginuser = "";

        // exit while loop if keypboard 'input' equals to 'quit'
        while(!input.equals("quit")) {
            input = con.readLine("What do you want to do? ");

            if (input.startsWith("login")) {
                Scanner scan = new Scanner(input.substring(6));

                while(scan.hasNext()) {
                    loginuser = scan.next();
                }

                // check if the file <loginuser> exists
                // if not exists, create a new file so that you can write to the file
                // else (maybe) override
                File loginFile = new File(dirPath + File.separator + loginuser);
                if (loginFile.exists()) {
                    System.out.println("File " + loginuser + " already exists");
                } else {
                    loginFile.createNewFile();
                }
            }

            if (input.equals("users")) {
                File directoryPath = new File(dirPath);

                String [] directoryListing = directoryPath.list();
                System.out.println("List of files and directories in the specific folder " + dirPath);
                for (String dirList : directoryListing) {
                    System.out.println(dirList);
                }
            }

            if (input.startsWith("add")) {
                input = input.replace(',', ' ');

                // 1. use FileWriter & PrintWriter to write to a <loginuser> file
                FileWriter fw = new FileWriter(dirPath + File.separator + loginuser, true);
                PrintWriter pw = new PrintWriter(fw);

                String currentScanString = "";
                Scanner inputScanner = new Scanner(input.substring(4));
                while(inputScanner.hasNext()) {
                    currentScanString = inputScanner.next();
                    cartItems.add(currentScanString);

                    // 2. write to file using PrintWriter
                    pw.write("\n" + currentScanString);
                }

                // 3. flush and close the fileWriter & PrintWriter objects
                pw.flush();
                pw.close();
                fw.close();

            }

            // user must be logged in i.e login corina
            if (input.equals("list")) {
                // 1. need a File class and BufferedReader class to read the cart items from the file
                File readFile = new File(dirPath + File.separator + loginuser);
                BufferedReader br = new BufferedReader(new FileReader(readFile));

                String readFileInput = "";

                //reset cart items list collection
                cartItems = new ArrayList<String>();

                // 2. while loop to read through all the item records in the file
                while((readFileInput = br.readLine()) !=null) {
                    System.out.println(readFileInput);
                    cartItems.add(readFileInput);
                }

                //3. exit from while loop - close buffered reader class/object
                br.close();
            }

            if (input.startsWith("delete")) {
                String [] stringVal = input.split(" ");

                int deleteIndex = Integer.parseInt(stringVal[1]);
                if ( deleteIndex <= cartItems.size()) {
                    cartItems.remove(deleteIndex);
                } else {
                    System.out.println("Index out of range error.");
                }

                FileWriter fw = new FileWriter(dirPath + File.separator + loginuser, false);
                BufferedWriter bw = new BufferedWriter(fw);

                int listIndex = 0;
                while (listIndex < cartItems.size()) {
                    bw.write(cartItems.get(listIndex));
                    bw.newLine();

                    listIndex++;
                }

                bw.flush();
                bw.close();
                fw.close();
            }
        }   
    }
}