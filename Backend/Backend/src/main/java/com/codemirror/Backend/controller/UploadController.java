package com.codemirror.Backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import com.codemirror.Backend.service.SimilarityService;

@RestController
public class UploadController {

    private final SimilarityService similarityService;

    public UploadController(SimilarityService similarityService){
        this.similarityService = similarityService;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){

        return similarityService.processZip(file);

    }

}