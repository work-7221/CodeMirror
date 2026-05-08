package com.codemirror.Backend.engine;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import com.codemirror.Backend.engine.similarity_commands.N_gram;

public class Main {

    public static List<Result> processFolder(String folderPath) {

        File theFolder = new File(folderPath);
        File[] list_of_files = theFolder.listFiles();
        if (list_of_files.length == 1 && list_of_files[0].isDirectory()) {
            list_of_files = list_of_files[0].listFiles();
        }

        HashMap<String, List<String>> ERN = new HashMap<>();

        for (File file : list_of_files) {

            if (file.isDirectory()) {
                continue;
            }

            String individual_files = file.getAbsolutePath();

            String output = Retriever.Extractor(file.getAbsolutePath());

            // String individual_files = file.getAbsolutePath();
            // String output = Retriever.Extractor(file.getAbsolutePath());

            List<String> each_file = Tokenizer.tokenizer(output);
            List<String> each_file_finalized_output_normalized = new ArrayList<>();

            if (individual_files.endsWith(".java")) {

                for (String each_token : each_file) {

                    List<String> buffer = Tokenizer_For_Non_W.tokenized_complicated(each_token);

                    for (String buffer_token : buffer) {

                        String normalized_token = Normalizer.normalized(buffer_token, "java");

                        each_file_finalized_output_normalized.addAll(
                                Tokenizer_For_Non_W.tokenized_complicated(normalized_token));
                    }
                }
            }

            if (individual_files.endsWith(".py")) {

                for (String each_token : each_file) {

                    List<String> buffer = Tokenizer_For_Non_W.tokenized_complicated(each_token);

                    for (String buffer_token : buffer) {

                        String normalized_token = Normalizer.normalized(buffer_token, "python");

                        each_file_finalized_output_normalized.addAll(
                                Tokenizer_For_Non_W.tokenized_complicated(normalized_token));
                    }
                }
            }

            ERN.put(individual_files, each_file_finalized_output_normalized);
        }

        HashMap<List<String>, Double> final_scores = N_gram.hash_iterator(ERN);

        return Result_producer.Result(final_scores);
    }
}