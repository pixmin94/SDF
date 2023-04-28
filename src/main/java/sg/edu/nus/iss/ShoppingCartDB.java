package sg.edu.nus.iss;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ShoppingCartDB {
     
    private List<String> users;
    private String currentDirectory;
    private String currentLoggedIn;

    public List<String> getUsers() { return users; }
    public void setUsers(List<String> users) { this.users = users; }
    public String getCurrentLoggedIn() { return currentLoggedIn; }
    public void setCurrentLoggedIn(String currentLoggedIn) { this.currentLoggedIn = currentLoggedIn; }

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

    public void save() {

    }

    public List<String> users() {
        
        return this.getUsers();
    }
}