package CodeMirror;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;

/*
for regular expression, 
we make a pattern class, and we compile the regular expression so as to make the instance of the pattern class

then we use matcher class to make a matcher objects and then do the further operations
*/

public class Tokenizer {
    public static List<String> tokenizer(String file_content) {
        String regex = "(\\w+ | \\W+)";
        List<String> storing = new ArrayList<>(); 

        Pattern pattern_instance = Pattern.compile(regex);
        Matcher matcher_instance = pattern_instance.matcher(file_content);


        while (matcher_instance.find()) {
            storing.add(matcher_instance.group(0));
        }

        return storing;
    }
}
