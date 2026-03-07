package com.codemirror.Backend.service;

import com.codemirror.Backend.engine.Main;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class SimilarityService {

    public String processZip(MultipartFile file) {

        try {

            File tempZip = new File("temp.zip");
            FileOutputStream fos = new FileOutputStream(tempZip);
            fos.write(file.getBytes());
            fos.close();

            System.out.println("ZIP received and saved");

            // extracting the content of the folde
            File extractDir = new File("extracted");
            if (!extractDir.exists()) {
                extractDir.mkdir();
            }

            unzip(tempZip, extractDir);

            System.out.println("ZIP extracted.");

            Main.processFolder(extractDir.getAbsolutePath());

            return "Similarity analysis completed";

            // next step: unzip + run engine

        } catch (Exception e) {
            e.printStackTrace();
            return "error reading this file";
        }
    }

    private void unzip(File zipFile, File destDir) throws IOException {

        byte[] buffer = new byte[1024];

        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {

            File newFile = new File(destDir, zipEntry.getName());

            if (zipEntry.isDirectory()) {
                newFile.mkdirs();
            } else {

                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
            }

            zipEntry = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();

    }

}