package CodeMirror;

import java.util.*;
/*
This file is supposed to take each tokens, and normalize them.

normalization: a process where source code is taken and transformed into a standaridized form by removing 
superficial differences while preserving the structural logic
*/


public class Normalizer {
    public static String normalized(String token, String path) {

        String output = "";

        String[] group_1 = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
"const", "continue", "default", "do", "double", "else", "enum", "extends", "final",
"finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int",
"interface", "long", "native", "new", "package", "private", "protected", "public",
"return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
"throw", "throws", "transient", "try", "void", "volatile", "while", ";" , "," , "." , "(" , ")" , "{" , "}" , "[","]",":", "main", "+",
 "-", "", "/", "%", "++", "--", "=", "+=", "-=", "=", "/=", "%=", "&=", "|^", "<<=", ">>=", ">>>=", "==", "!=", ">", "<", ">=", "<=",
  "&&", "||", "!", "&", "|", "^", "~", "<<", ">>", ">>>", "?:", "*", "*="
}; 

        String[] group_2 = {"False", "None", "True", "and", "as", "assert", "break", "class", "continue", "def",
"del", "elif", "else", "except", "finally", "for", "from", "global", "if", "import",
"in", "is", "lambda", "nonlocal", "not", "or", "pass", "raise", "return", "try","(", ")", "{", "}", "[", "]", ";", ":", ".",
"while", "with", "yield", "=", "+", "-", "*", "/", "//", "**", "%", "==", "!=", "<", "<=", ">", ">=", "and","or", "not", "&", "|", "~", "^", ">>", "<<", "in", "not in", "is", "not is", "+=", "-=", "*=", "/=", "//=", "**=", "%=", "[", "]", "[:]"
};

    // if (!Arrays.asList(group_2).contains(token) && path == "python"){
    //     output = "ID";
    // } else {
    //     output = token;
    // }

    // if (!Arrays.asList(group_1).contains(token) && path == "java"){
    //         output = "ID";
    // } else {
    //         output = token;
    // }
    
    if (path == "python") {
        if(!Arrays.asList(group_2).contains(token)){
             output = "ID";
    } else {
        output = token;
        }
    }
    
    if (path == "java") {
        if(!Arrays.asList(group_1).contains(token)){
             output = "ID";
    } else {
        output = token;
        }
    }
       

    
    return output;
}}