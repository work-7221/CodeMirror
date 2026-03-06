package CodeMirror;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Retriever {

    // making a function which will be extracting the texts from the file.

    public static String Extractor(String path) {
        //method body
        File file = new File(path);
        String file_path_string = file.getAbsolutePath();

        Path file_path_object = Paths.get(file_path_string);
        String content = "";

        try{
            content = Files.readString(file_path_object);
        } catch (IOException e) {
            System.out.println("Error reading this file: " + e.getMessage());
            e.printStackTrace();
        }
        return content;
    }
}
