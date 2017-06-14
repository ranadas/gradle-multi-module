package com.rdas.service;

import org.apache.tika.Tika;
import org.apache.tika.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

/**
 * Created by rdas on 12/06/2017.
 */
@Service
public class FileDetectionService {

    private final Logger logger = LoggerFactory.getLogger(FileDetectionService.class);

//    @Autowired
//    private Parser parser ;
    //        parser.parse(new ByteArrayInputStream("Hello, World!".getBytes(UTF_8)),
//                new WriteOutContentHandler(System.out), new Metadata(),
//                new ParseContext());

    public String mimeForFile(File file) {
//        Tika tika = new Tika();
//        String detectedType = tika.detect(file.getBytes());
//        System.out.println(detectedType);
        return ("WIP");
    }
}
