package com.codemirror.Backend.engine;

import java.util.*;
import java.io.File;

// import java.util.ArrayList;

public class Result_producer {

    public static List<Result> Result(HashMap<List<String>, Double> final_scores) {

        List<Result> results = new ArrayList<>();

        for (Map.Entry<List<String>, Double> entry : final_scores.entrySet()) {

            List<String> files = entry.getKey();
            double score = entry.getValue();

            String file1 = new File(files.get(0)).getName();
            String file2 = new File(files.get(1)).getName();

            results.add(new Result(file1, file2, score));

        }

        return results;
    }
}