package com.example.restservice.service;

import com.example.restservice.Interface.CsvService;
import com.example.restservice.entity.BaseTranscation;
import com.example.restservice.entity.EntireTranscation;
import com.example.restservice.entity.Transcation;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvServiceImp implements CsvService {

    private static final Logger logger = LoggerFactory.getLogger(ApiServiceImp.class);

    private static final String CSV_SEPARATOR = ",";

    @Autowired
    private ApiServiceImp apiServiceImp;


    public List<BaseTranscation> uploadCsvToBaseTransaction(MultipartFile file) {
        List<BaseTranscation> transcations = new ArrayList<>();
        try {
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CsvToBean<BaseTranscation> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(BaseTranscation.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            transcations = csvToBean.parse();
        } catch (Exception exception) {
            logger.warn("Error was throwing when trying to upload CSV file: {}", exception, exception.getMessage());
        }
        return transcations;
    }

    @Override
    public File csvEntireTransaction(MultipartFile multipatFile) {
        List<EntireTranscation> transcationList = new ArrayList<>();
        Transcation transcationSave;
        EntireTranscation entireTranscation;
        File fileTranscation = null;
        List<BaseTranscation> baseTranscationsList = uploadCsvToBaseTransaction(multipatFile);
        for (BaseTranscation baseTranscation : baseTranscationsList) {
            transcationSave = apiServiceImp.apiIpStack(baseTranscation.getIp());
            entireTranscation = new EntireTranscation(baseTranscation, transcationSave);
            transcationList.add(entireTranscation);
        }
        EntireTranscation[] entireTranscations = new EntireTranscation[transcationList.size()];
        transcationList.toArray(entireTranscations);
        try {
            fileTranscation = writeAllTrascation(entireTranscations);
        } catch (Exception exception) {
            logger.warn("Error was throwing when trying to upload CSV file: {}", exception, exception.getMessage());
        }
        if (fileTranscation == null) {
            logger.warn("the CSV file is empty please check csvEntireTransaction method");
        }
        return fileTranscation;
    }

    @Override
    public File writeAllTrascation(EntireTranscation[] transcationList) {
        File file = null;
        try {
            String[] array = {"id", "userId", "name", "amount", "ip", "type", "continentCode", "countryCode", "countryName",
                    "regionCode", "regionName", "city", "zip", "latitude", "longitude", "geonameId",
                    "capital", "languagesCode", "languagesName", "country_flag", "country_flag_emoji",
                    "country_flag_emoji_unicode", "calling_code", "is_eu"};
            EntireTranscation transcationToFile;
            file = new File("C:\\Users\\Shirela\\webSocket\\bit interview", "transcationApiFruad.csv");
            BufferedWriter oneLine = new BufferedWriter(new FileWriter(file));
            for (int j = 0; j < array.length; j++) {
                oneLine.write(array[j]);
                oneLine.append(CSV_SEPARATOR);
            }
            oneLine.newLine();
            for (int i = 0; i < transcationList.length; i++) {
                transcationToFile = transcationList[i];
                oneLine.append("\r\n");
                oneLine.append(transcationToFile.getBaseTranscation().getId());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getBaseTranscation().getUserId());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getBaseTranscation().getName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(String.valueOf(transcationToFile.getBaseTranscation().getAmount()));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getIp());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getType());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getContinent_code());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getContinent_name());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getCountry_code());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getCountry_name());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getRegion_name());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getRegion_code());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getCity());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getZip());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(String.valueOf(transcationToFile.getTranscation().getLatitude()));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getLocation().getCountry_flag());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(String.valueOf(transcationToFile.getTranscation().getLocation().getGeoname_id()));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getLocation().getCapital());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getLocation().getLanguages().get(0).getCode());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getLocation().getLanguages().get(0).getName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getLocation().getCountry_flag());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getLocation().getCountry_flag());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getLocation().getCountry_flag_emoji());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getLocation().getCountry_flag_emoji_unicode());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(transcationToFile.getTranscation().getLocation().getCalling_code());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(String.valueOf(transcationToFile.getTranscation().getLocation().isIs_eu()));
                oneLine.append(CSV_SEPARATOR);
                oneLine.newLine();
            }
            oneLine.flush();
            oneLine.close();
        } catch (IOException exception) {
            logger.warn("Error was throwing when trying to upload CSV file: {}", exception, exception.getMessage());
        }
        return file;
    }


}
