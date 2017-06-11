package com.rdas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by rdas on 11/06/2017.
 */
@Service
public class FileUploaderService {
    private final Logger logger = LoggerFactory.getLogger(FileUploaderService.class);
    private static String UPLOADED_FOLDER = "tempFileUploads/";

    public void saveUploadedFiles(List<MultipartFile> files) throws Exception {
        files.stream()
                .filter(file -> !file.isEmpty())
                .forEach(file -> writeMultipartFile(file));
        /*
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                writeMultipartFile(file);
            }
        }*/
    }

    private void writeMultipartFile(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Path write = Files.write(path, bytes);
            logger.debug("written : {}", write.toAbsolutePath().toString());
        } catch (IOException ioe) {
            logger.error("Error saving file {}", ioe);
            throw new RuntimeException(ioe);
        }
    }
}
