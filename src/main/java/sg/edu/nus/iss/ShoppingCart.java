package sg.edu.nus.iss;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
// import java.util.regex.*;  
import java.util.Scanner;

public class ShoppingCart {
    // static String cartArray[] = new String[];
    public static void main( String[] args )
    {
        System.out.println("Welcome to Shopping Cart!");

        List<String> cartItems = new ArrayList<String>();

        Console con = System.console();
        String input = "";

        while (!input.equals("quit")) {
            input = con.readLine(">");

            if (input.startsWith("add")) {
                input = input.replace(',', ' ');
                Scanner scan = new Scanner(input.substring(4));

                String content = "";
                while (scan.hasNext()) {
                    content = scan.next();
                    cartItems.add(content);
                }
            }

            if (input.equals("list")) {
                int i = 0;
                for (String item : cartItems) {
                    i++;
                    System.out.println(i + ". " + item);
                }
            }

            if (input.startsWith("delete")) {
                Scanner scan = new Scanner(input.substring(7));

                String content = "";
                while (scan.hasNext()) {
                    content = scan.next();

                    int listIndex = Integer.parseInt(content);

                    if (listIndex-1 < cartItems.size()) {
                        cartItems.remove(listIndex-1);
                    } else {
                        System.err.println("Incorrect item index");
                    }
                }
            }
        }
        System.out.println("Thank you, please come again :)");
    }

}

    // Console con = System.console();
    // ArrayList<String> cartArray = new ArrayList<String>();
    // String input = con.readLine("Welcome to your shopping cart\n ");
    // input = input.trim();
    // String[] splitString = input.split(" "); 


    // System.out.println(Arrays.toString(splitString));

    // if ("list".equals(splitString[0])) {
    //     for (int counter = 0; counter < cartArray.size(); counter++) {
    //         String output = counter + "." + cartArray.get(counter);
    //         System.out.println(output);
    //         System.out.println(cartArray);
    //     } 
        
    // }   else if ("add".equals(splitString[0])) {
    //     cartArray.add(splitString[1]);
    //     System.out.println(cartArray);
    // } else if ("delete".equals(splitString[0])) {

    // }

    // switch(input) {
    //     case "list":
    //     for (int counter = 0; counter < cartArray.length; counter++) {
    //         String output = counter + "." + cartArray[counter];
    //         System.out.println(output);	
    //     } 
    //         break;
    //     case (Pattern.matches(".add", input)):
    //         System.out.println(input);
    //         break;    
    // }