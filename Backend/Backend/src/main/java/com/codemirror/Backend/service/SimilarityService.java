package com.codemirror.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.io.FileOutputStream;
import java.io.InputStream;

@Service
public class SimilarityService {

    public String processZip(MultipartFile file) {

        try {

            File tempFile = File.createTempFile("upload", ".zip");

            file.transferTo(tempFile);

            System.out.println("ZIP saved at: " + tempFile.getAbsolutePath());

            return "ZIP received successfully";

        } catch (IOException e) {
            e.printStackTrace();
            return "Error processing ZIP";
        }

        File extractDir = new File("extracted");
        extractDir.mkdir();

        extractZip(tempFile, extractDir);

    }

    public void extractZip(File zipFile, File destDir) throws IOException {

        byte[] buffer = new byte[1024];

        ZipInputStream zis = new ZipInputStream(new java.io.FileInputStream(zipFile));

        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {

            File newFile = new File(destDir, zipEntry.getName());

            FileOutputStream fos = new FileOutputStream(newFile);

            int len;

            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }

            fos.close();

            zipEntry = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();
    }
}