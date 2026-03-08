package com.codemirror.Backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.codemirror.Backend.engine.Result;

import com.codemirror.Backend.service.SimilarityService;

@RestController
public class UploadController {

    private final SimilarityService similarityService;

    public UploadController(SimilarityService similarityService){
        this.similarityService = similarityService;
    }

    @PostMapping("/upload")
    public List<Result> upload(@RequestParam("file") MultipartFile file){

        return similarityService.processZip(file);

    }

}