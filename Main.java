package CodeMirror;

import javax.swing.*;
import java.io.File;

import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        System.out.println("Now we proceed to choose the folder where you have the file that you want to check for!!");
        
        JFileChooser chooser = new JFileChooser();
        
        // setting the dialog box to only allow directory selection!
        
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // setting the title of the file dialog box

        chooser.setDialogTitle("Select A Folder");

        // show the dialog box and get the result 
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            System.out.println("Here's the selected folder: " + selectedFolder.getAbsolutePath());

            // storing the directory path for later use.
            String Path = selectedFolder.getAbsolutePath();
            File theFolder = new File(Path);
            
            File[] list_of_files = theFolder.listFiles(); // an array consisting of File instances of the directory where we have stored the files

            HashMap<String, String> ERN = new HashMap<>();
            
            //now getting the hold of the files in the selected directory
            for (File file : list_of_files) {

                String individual_files = file.getAbsolutePath(); 
                String output = Retriever.Extractor(file.getAbsolutePath());
                

                System.out.println(Tokenizer.tokenizer(output));
                System.out.println("=========================");
                ERN.put(individual_files, output);
                // now using tokenizer class to implement tokenization for each of the files in the directory.

            }
        } else {
            System.out.println("File Selection Cancelled.");
        }
    }
}