package CodeMirror;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Retriever {

    // making a function which will be extracting the texts from the file.

    public static String Extractor(String path) {
        //method body
        File file = new File(path);
        String totality = "";
        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                totality += sc.next();
                totality += "\n";
            } 

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
    }
        return totality;
        
    }
}
