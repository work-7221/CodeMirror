package CodeMirror;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Tokenizer_For_Non_W {
    public static List<String> tokenized_complicated(String something) {

        if (something.matches("\\W+")){
            String regex = "";
            List<String> output = Arrays.asList(something.split(regex));
            return output;
        } else {
            List<String> output = new ArrayList<>();
            output.add(something);
            return output;
        }
    }
}
    // public static void main(String[] args) {
        // String[] answer = tokenized_complicated("(a()");
// 
        // for (String x : answer) {
            // System.out.println(x);
        // }
    // }
