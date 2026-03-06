package CodeMirror.similarity_commands;
import java.util.*;

public class N_gram {
    public static List<List<String>> n_gram(int n, List<String> normalized_tokens) {
        int j = n;
        int length = normalized_tokens.size();
        List<List<String>> list = new ArrayList<>();

        // applying sliding window technique in order to get the N grams of each file
        for (int i = 0; i < length; i++) {
            
            if (j >= length) {
                break;
            } else {
                List<String> chunk = new ArrayList<>(normalized_tokens.subList(i, j));
                list.add(chunk);  
                j += 1;
            }
        }
        return list;
    }
    
    protected static double n_gram_score(List<String> file_a, List<String> file_b) {

        // starting with jaccard similarity and making sets with n grams as it's tokens
        Set<List<String>> A = new HashSet<List<String>>(); //FILE A
        List<List<String>> A_L = n_gram(3, file_a);  
        for (List<String> each: A_L) {
            A.add(each);
        }

        Set<List<String>> B = new HashSet<List<String>>(); //FILE B
        List<List<String>> B_L = n_gram(3, file_b);  
        for (List<String> each: B_L) {
            B.add(each);
        }

        // finding the intersection.
        Set<List<String>> intersection = new HashSet<List<String>>(A); 
        intersection.retainAll(B);

        //finding the union.
        Set<List<String>> union = new HashSet<List<String>>(A); 
        union.addAll(B);

        // accessing the number of elements in both union and intersection of the sets.
        double n_intersection = intersection.size();
        double n_union = union.size();

        // applying jaccard similarity.
        double score = (n_intersection/n_union)*100;

        return score;
    }

    public static HashMap<List<String>, Double> hash_iterator(HashMap<String, List<String>> hash) { //this method accepts hashmap {<FILE_PATH> : <NORMALIZED_TOKENS>}

        HashMap<List<String>, Double> stored_scores = new HashMap<>();

        for(Map.Entry<String, List<String>> entry_a : hash.entrySet()) {
            String path_a = entry_a.getKey();
            List<String> content_a = hash.get(path_a);

            for(Map.Entry<String, List<String>> entry_b : hash.entrySet()) {
                String path_b = entry_b.getKey();
                List<String> content_b = hash.get(path_b);
                if (path_a.endsWith(".java") && path_b.endsWith(".java")){
                    if (!path_a.equals(path_b)){ //ensuring that same file doesn't get compared with itself during the nested loop iteration.
                        // the score between path_a and path_b has been computed till here
                        double score = n_gram_score(content_a, content_b);
                        List<String> both_paths = new ArrayList<>();
                        both_paths.add(path_a);
                        both_paths.add(path_b);
                        stored_scores.put(both_paths, score);
                    }
                }
            }
        }

        return stored_scores;

    }
}