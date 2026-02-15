package CodeMirror;

import javax.swing.*;
import java.io.File;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import CodeMirror.similarity_commands.N_gram;
import CodeMirror.similarity_commands.similarity_checker;

public class Main{
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

            HashMap<String, List<String>> ERN = new HashMap<>();
            
            //now getting the hold of the files in the selected directory
            for (File file : list_of_files) {

                String individual_files = file.getAbsolutePath(); 
                String output = Retriever.Extractor(file.getAbsolutePath());
                
                List<String> each_file = Tokenizer.tokenizer((output)); //each file roughly tokenized and stored into a list

                List<String> each_file_finalized_output_normalized = new ArrayList<>(); // an empty list which is going to store the finalized tokens

                if (individual_files.endsWith(".java")){
                    for (String each_token : each_file){ // each token from each file 
                        List<String> buffer = Tokenizer_For_Non_W.tokenized_complicated(each_token); // now a we tokenized the non words into into individual non words (";;;\\" --> "; ; ; \ \" )
                        for (String buffer_token : buffer){
                            String normalized_token = Normalizer.normalized(buffer_token, "java"); // now each cleand buffer token is stored is now going through normalization process.
                            each_file_finalized_output_normalized.addAll(Tokenizer_For_Non_W.tokenized_complicated(normalized_token)); //then finally, the [normalized token] is extended into the each_file_finalized_output list. 
                            }
                        }
                    }
                
                if (individual_files.endsWith(".py")){
                    for (String each_token : each_file){ // each token from each file 
                        List<String> buffer = Tokenizer_For_Non_W.tokenized_complicated(each_token); // now a we tokenized the non words into into individual non words (";;;\\" --> "; ; ; \ \" )
                        for (String buffer_token : buffer){
                            String normalized_token = Normalizer.normalized(buffer_token, "python"); // now each cleand buffer token is stored is now going through normalization process.
                            each_file_finalized_output_normalized.addAll(Tokenizer_For_Non_W.tokenized_complicated(normalized_token)); //then finally, the [normalized token] is extended into the each_file_finalized_output list. 
                            }
                        }
                    }

                ERN.put(individual_files, each_file_finalized_output_normalized); //we have stored file path and the noramlized files togeteher in a hashmap in the form of key and value pairs.
                // now using tokenizer class to implement tokenization for each of the files in the directory.
                //goal is to match the file extension and the suitable tokens with each of the file.
                }
            // System.out.println(ERN);
            for (Map.Entry<?, ?> entry : ERN.entrySet()) {
                System.out.printf("%-15s : %s%n", entry.getKey(), entry.getValue());
                System.out.println("=========================");
            }
            System.out.println("*******************************************************************************************************************************************************************************");
            // similarity_checker.similarity_function(ERN);
            // System.out.println("*******************************************************************************************************************************************************************************");
            N_gram.hash_iterator(ERN);
            
        } else {
            System.out.println("File Selection Cancelled.");
        }
    }
}