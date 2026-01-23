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

            // storing the directory for later use.
            String Path = selectedFolder.getAbsolutePath();
            File directory = new File(Path);
            
            File[] list_of_files = directory.listFiles();

            // Retriever obj = new Retriever();

            HashMap<String, String> ERN = new HashMap<>();
            
            //now getting the hold of the files in the selected directory
            for (File file : list_of_files) {
                System.out.println("Path of file: " + file.getAbsolutePath());
                String output = Retriever.Extractor(file.getAbsolutePath());
                
                String individual_files = file.getAbsolutePath(); 

                System.out.println(output);
                System.out.println("======SEPARATION======");
                ERN.put(individual_files, output);
            }
            // System.out.println(ERN);

        } else {
            System.out.println("File Selection Cancelled.");
        }
        



    }
}
