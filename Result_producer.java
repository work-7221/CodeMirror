package CodeMirror;

import java.util.*;

public class Result_producer {
    public static String Result(HashMap<List<String>, Double> hash) {

        for (Map.Entry<List<String>, Double> score_key : hash.entrySet()) {

            List<String> stored_paths = score_key.getKey();

            double score = hash.get(stored_paths);

            String file_a = (stored_paths).get(0);
            String file_b = (stored_paths).get(1);

            
            if (score > 75.0) {
                System.out.println("Highly suspecious! " +  file_a +" matches with "+ file_b + " by " + score+"%");
            } else if (score > 50.0 && score < 75.0 ) {
                System.out.println("Mildly suspecious! " +  file_a +" matches with "+ file_b + " by " + score+"%");

            } else {
                System.out.println("Less suspecious! " +  file_a +" matches with "+ file_b + " by " + score+"%");

            }
            // System.out.println(file_a + " ||| " + file_b + " " +score);
        }

        return "";
    }
}
