package sg.edu.nus.iss;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class ShoppingCart2 {

    public static void main( String[] args )
    {
        System.out.println("Hello, welcome to shopping cart!");
        List<String> cartList = new ArrayList<>();

        Console con = System.console();
        String input = "";

        
        while (!input.equals("quit")) {
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

            }
        }

        System.out.println("Thank you, please come again :)");
    }

}
