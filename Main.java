package CodeMirror;

import javax.swing.*;

import java.io.File;
import java.io.File.*;


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

            for (File file : list_of_files) {
                System.out.println("Path of file: " + file.getAbsolutePath());
            }

        } else {
            System.out.println("File Selection Cancelled.");
        }
        
        //now getting the hol dof the files in the selected directory



    }
}
