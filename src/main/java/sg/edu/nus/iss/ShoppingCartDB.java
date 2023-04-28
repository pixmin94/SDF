package sg.edu.nus.iss;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class ShoppingCartDB {
     
    private String currentDirectory;
    private String currentLoggedIn;

    public String getCurrentLoggedIn() { return currentLoggedIn; }
    public void setCurrentLoggedIn(String currentLoggedIn) { this.currentLoggedIn = currentLoggedIn; }
    public String getCurrentDirectory() { return currentDirectory; }
    public void setCurrentDirectory(String currentDirectory) { this.currentDirectory = currentDirectory; }

    public ShoppingCartDB(String directory) {
        this.currentDirectory = directory;
    }

    public void login(String username) throws IOException {
        this.currentLoggedIn = username;
        File userLogin = new File(this.currentDirectory + File.separator + username);
        if(userLogin.exists()) {
            System.out.println("Logging in to user "+username+"...");
        } else {
            System.out.println("Creating new user "+username+"...");
            userLogin.createNewFile();
        }
    }

    public void save(List<String> cartList) throws IOException {
        FileWriter fw = new FileWriter(this.currentDirectory + File.separator + this.currentLoggedIn, true);
        BufferedWriter bw = new BufferedWriter(fw);

        int i = 0;
        while (i < cartList.size()) {
            bw.write(cartList.get(i));
            bw.newLine();
            i++;
        }
        bw.flush();
        bw.close();
        fw.close();
        System.out.println("Saved to "+this.currentLoggedIn+" db!");
    }

    // public List<String> users() {
    //     return this.getUsers();
    // }
}