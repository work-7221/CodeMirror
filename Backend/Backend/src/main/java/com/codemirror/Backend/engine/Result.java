package com.codemirror.Backend.engine;

public class Result {

    private String file1;
    private String file2;
    private double similarity;

    public Result(String file1, String file2, double similarity) {
        this.file1 = file1;
        this.file2 = file2;
        this.similarity = similarity;
    }

    public String getFile1() {
        return file1;
    }

    public String getFile2() {
        return file2;
    }

    public double getSimilarity() {
        return similarity;
    }
}