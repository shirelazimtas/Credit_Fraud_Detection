package com.example.restservice.Interface;


import com.example.restservice.entity.EntireTranscation;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


public interface CsvService {

    File writeAllTrascation(EntireTranscation[] transcationList);

    File csvEntireTransaction(MultipartFile multipatFile);

}
