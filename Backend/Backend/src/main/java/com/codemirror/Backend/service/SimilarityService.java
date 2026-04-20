package com.codemirror.Backend.service;

import com.codemirror.Backend.engine.Main;
import com.codemirror.Backend.engine.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class SimilarityService {

    public List<Result> processZip(MultipartFile file) {

        try {

            File zipFile = new File("upload.zip");
            if (zipFile.exists()) {
                zipFile.delete();
            }

            File extractDir = new File("extracted");
            if (extractDir.exists()) {
                deleteDirectory(extractDir);
            }

            FileOutputStream fos = new FileOutputStream(zipFile);
            fos.write(file.getBytes());
            fos.close();

            extractDir.mkdir();

            unzip(zipFile, extractDir);

            // 🔥 STEP 5: Process
            List<Result> results = Main.processFolder(extractDir.getAbsolutePath());

            return results;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                deleteDirectory(file);
            }
        }
        dir.delete();
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