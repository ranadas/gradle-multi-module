package com.rdas.controller;

import com.rdas.service.FileUploaderService;
import com.rdas.util.Loggable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * https://www.mkyong.com/spring-boot/spring-boot-file-upload-example-ajax-and-rest/
 * Created by rdas on 10/06/2017.
 */
@RestController
@Api(value = "File Uploader Controller", description = "Controller to upload files")
public class FileUploadController {
    private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileUploaderService fileUploaderService;

    @Loggable(Loggable.DEBUG)
    @ApiOperation(value = "api/upload.", notes = "upload a single file.")
    @PostMapping("/api/upload")
    public ResponseEntity<?> uploadSingleFile(@RequestParam("file") MultipartFile uploadedFile) {
        logger.debug("Single file upload!");

        if (uploadedFile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {
            fileUploaderService.saveUploadedFiles(Arrays.asList(uploadedFile));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " + uploadedFile.getOriginalFilename(),
                new HttpHeaders(),  HttpStatus.OK);

    }

    @Loggable(Loggable.DEBUG)
    @ApiOperation(value = "api/upload/multi", notes = "upload multiple files")
    @PostMapping("/api/upload/multi")
    public ResponseEntity<?> uploadFileMulti(
            @RequestParam("extraField") String extraField,
            @RequestParam("files") MultipartFile[] uploadfiles) {

        logger.debug("Multiple file upload!");

        // Get file name
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {
            fileUploaderService.saveUploadedFiles(Arrays.asList(uploadfiles));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " + uploadedFileName, HttpStatus.OK);

    }
}
