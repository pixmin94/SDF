package sg.edu.nus.iss;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

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

        List<String> cartList = new ArrayList<>();
        ShoppingCartDB directory = new ShoppingCartDB(dirName);
        Console con = System.console();
        String username = "";
        String input = "";

        username = con.readLine("Please login:");
        directory.login(username);

        while (!input.equals("quit")){
            input = con.readLine(">");

            if (input.startsWith("add")) {
                Scanner scan = new Scanner(input.substring(4));

                String content = "";
                while (scan.hasNext()) {
                    content = scan.next();
                    cartList.add(content);
                    System.out.println("Added "+content+" to cart list!");
                }

            } else if (input.equals("list")) {
                int i = 1;
                for (ListIterator<String> iter = cartList.listIterator(); iter.hasNext();) {
                    String item = iter.next();
                    System.out.println(i+". "+item);
                    i++;
                }

            } else if (input.startsWith("delete")) {
                Scanner scan = new Scanner(input.substring(7));
                int index = Integer.parseInt(scan.next()) - 1;
                System.out.println("deleting "+(index+1)+". "+cartList.get(index));
                cartList.remove(index);

            } else if (input.equals("save")) {
                directory.save(cartList);
                
            }
            
        }
        System.out.println("Thank you, please come again :)");
    }
}
