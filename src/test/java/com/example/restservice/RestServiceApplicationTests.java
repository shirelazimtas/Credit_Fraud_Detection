package com.example.restservice;

import com.example.restservice.entity.*;
import com.example.restservice.service.ApiServiceImp;
import com.example.restservice.service.CsvServiceImp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RestServiceApplicationTests {

    @Autowired
    ApiServiceImp apiServiceImp;

    @Autowired
    CsvServiceImp csvServiceImp;

    @Test
    void apiIpStackTest() {
        String capitalCity = "Jerusalem";
        String ipAddress = "89.139.224.58";
        Transcation transcation = apiServiceImp.apiIpStack(ipAddress);
        assertNotNull(transcation, "The transcation of " + ipAddress + " was null");
        assertEquals(capitalCity, transcation.getLocation().getCapital(), "The information of api request for " + ipAddress + " was wrong");
    }

    @Test
    void writeAllTrascationTest() {
        List<Languages> languages = new ArrayList<Languages>();
        languages.add(new Languages("123", "123", "dfgvd"));
        Location location = new Location(120, "jj", languages, "country_flag_emoji", "country_flag_emoji_unicode", "calling_code", "true", true);
        BaseTranscation baseTranscation = new BaseTranscation("1", "123", "shirel", 123, "89.139.224.58");
        Transcation transcation = new Transcation("89.139.224.58", "type", "continent_code", "continent_name", "country_code", "country_name", "region_code",
                "region_name", "city", "zip", 12.2, 13.3, location);
        EntireTranscation entireTranscation = new EntireTranscation(baseTranscation, transcation);
        EntireTranscation[] transcationList = new EntireTranscation[1];
        transcationList[0] = entireTranscation;
        File file = csvServiceImp.writeAllTrascation(transcationList);
        assertNotNull(file.isFile(), "The file is empty");
    }

    @Test
    void csvEntireTransactionTest() {
        String name = "transcationApiFruad.csv";
        String originalFileName = "file.txt";
        String contentType = "text/plain";
        byte[] content = null;
        MultipartFile multipartFile = new MockMultipartFile(name,
                originalFileName, contentType, content);
        File csvFile = csvServiceImp.csvEntireTransaction(multipartFile);
        assertNotNull(csvFile, "The file is empty");
    }

}
