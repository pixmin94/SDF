package sg.edu.nus.iss;
import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.regex.*;  

public class ShoppingCart {
    // static String cartArray[] = new String[];
    public static void main( String[] args )
    {
        Console con = System.console();
        ArrayList<String> cartArray = new ArrayList<String>();
        String input = con.readLine("Welcome to your shopping cart\n ");
        input = input.trim();
        String[] splitString = input.split(" "); 


        System.out.println(Arrays.toString(splitString));

        if ("list".equals(splitString[0])) {
            for (int counter = 0; counter < cartArray.size(); counter++) {
                String output = counter + "." + cartArray.get(counter);
                System.out.println(output);
                System.out.println(cartArray);
            } 
            
        }   else if ("add".equals(splitString[0])) {
            cartArray.add(splitString[1]);
            System.out.println(cartArray);
        } else if ("delete".equals(splitString[0])) {

        }

    }

}

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