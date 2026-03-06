package CodeMirror.similarity_commands;

import java.util.*;


public class similarity_checker {
    public static int similarity_function(HashMap<String, List<String>> hash){ //accepts the hashmap containing {file addresses : normalized}

        for(Map.Entry<?, ?> entry : hash.entrySet()){
            String path = entry.getKey().toString();
            List<String> normalized_tokens = hash.get(path);


            // making a set A which contains all the token of the file which we are holding onto
            Set<String> A = new HashSet<String>();
            A.addAll(normalized_tokens);

            // now the goal is to shift 
            for(Map.Entry<?, ?> inside_entry : hash.entrySet()){
                String path_inside =  inside_entry.getKey().toString();
                if (path.endsWith(".java") && path_inside.endsWith(".java")){
                    List<String> normalized_tokens_inside = hash.get(path_inside);

                    // this is the Set which we are comparing with A to. this is the file that changes with each iteration.
                    Set<String> B =  new HashSet<String>();
                    B.addAll(normalized_tokens_inside);

                    // finding the intersectino of the sets A and B which contains the tokens
                    Set<String> intersection = new HashSet<>(A);
                    intersection.retainAll(B);
                    
                    Set<String> union = new HashSet<>(A);
                    union.addAll(B);    
                    
                    //number of elements in A
                    double A_n = intersection.size();

                    //number of element in B
                    double B_n = union.size();

                    // applied jaccard similarity
                    //the jaccard similarity of file A with file B tokens is 
                    double Jaccard_Similarity_Score = (A_n/B_n)*100;

                    // System.out.println(path + " |+| " + path_inside + " |=| " + A_n + " |&&&| " + B_n);
                    System.out.println(Jaccard_Similarity_Score);

                    System.out.println( "========");

                }
            }
            
        }
        return 0;
    }
}
