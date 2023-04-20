package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        String dirPath = args[0];
        String fileName = args[1];
        String dirPathFileName = dirPath + File.separator + fileName;
        System.out.println(dirPathFileName);

        File newDir = new File(dirPath);
        if (newDir.exists()) {
            System.out.println("Accessing " + dirPath + " directory");
        } else {
            newDir.mkdir();
        }

        File newFile = new File(dirPathFileName);
        if (!newFile.exists()) {
            System.out.println(dirPathFileName + "file does not exist");
            System.exit(0);
        }

        // 1. use FileReader and BufferedReader to read file
        FileReader fr = new FileReader(new File(dirPathFileName));
        BufferedReader br = new BufferedReader(fr);
        
        StringBuilder sbFileContent = new StringBuilder();
        String lineInput = "";
        // 2. while loop to read file into a string variable
        while ((lineInput = br.readLine()) != null) {
            lineInput = lineInput.toLowerCase();
            sbFileContent.append(lineInput);
        }

        // 3. close the readers
        br.close();
        fr.close();

        String fileContent = sbFileContent.toString();
        fileContent = fileContent.replaceAll("\\p{P}", " ");

        String [] fileContentArray = fileContent.split(" ");
        Map<String, Integer> words = new HashMap<>();
        for (String word: fileContentArray) {
            Integer wordCount = words.get(word);
            if (wordCount == null) {
                words.put(word, 1);
            } else {
                words.put(word, wordCount + 1);
            }
        }

        System.out.println(fileContent);
        System.out.println(words);
    }
}
