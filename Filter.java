package CodeMirror;

import java.util.List;
// import java.util.ArrayList;
// import java.util.Collection;

public class Filter {
    public static List<String> filter(List<String> tokens_list){
        
        List<String> dummy = tokens_list;

        for (int i = 0; i < tokens_list.size(); i++) {
            
            /*
            a condition is required where we need the characters in the token WHERE spaces are also there.
            
            we need something which checks for each token that whether it has characters excluding spaces

            the current condition only checks if space is in the tokens -> therefore it eliminates the 
            valuable characters in the token 

            */
        //    if (tokens_list.get(i).contains(" ")) {
        //         dummy.remove(tokens_list.get(i))   
        //    }
        //    if (tokens_list.get(i).contains(" ")) {
        //        // dummy.remove(tokens_list.get(i));
        //        System.out.println("yes" + tokens_list.get(i));
        //    }

            if (tokens_list.get(i).contains(" ")) {
                dummy.set(i, dummy.get(i).replaceAll("\\s+", ""));
            }
        }      
        return dummy;
    }
}