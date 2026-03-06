package com.codemirror.Backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class SimilarityService {

    public String processZip(MultipartFile file) {

        try {

            File tempZip = new File("temp.zip");
            FileOutputStream fos = new FileOutputStream(tempZip);
            fos.write(file.getBytes());
            fos.close();

            System.out.println("ZIP received and saved");
            
            return "Similarity analysis completed";

            // next step: unzip + run engine

        } catch (Exception e) {
            e.printStackTrace();
            return "error reading this file";
        }

    }
}