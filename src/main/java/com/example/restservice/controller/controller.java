package com.example.restservice.controller;

import com.example.restservice.entity.Greeting;
import com.example.restservice.Interface.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;


import java.io.*;


@RestController
@EnableCaching
public class controller {

    @Autowired
    private CsvService csvUploadService;

    public File file;

    public String messageNotification = "There was no changes in the file";

    @GetMapping("/fraud/transaction")
    public ResponseEntity returnLastCSV() {
        return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=" + "transcationApiFruad" + ".csv")
                .contentLength(file.length()).contentType(MediaType.parseMediaType("text/csv")).body(new FileSystemResource(file));
    }

    @PostMapping("/fraud/transaction")
    public ResponseEntity uploadData(@RequestParam("file") MultipartFile multipatFile) {
        file = csvUploadService.csvEntireTransaction(multipatFile);
        messageNotification = "There was an change in csv file at " + java.time.LocalDateTime.now();
        return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=" + "transcationApiFruad" + ".csv")
                .contentLength(file.length()).contentType(MediaType.parseMediaType("text/csv")).body(new FileSystemResource(file));
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greet() throws InterruptedException {
        Thread.sleep(2000);
        return new Greeting("Hello, " +
                HtmlUtils.htmlEscape(messageNotification));
    }

}
