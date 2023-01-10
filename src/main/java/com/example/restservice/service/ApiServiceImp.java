package com.example.restservice.service;

import com.example.restservice.Interface.ApiService;
import com.example.restservice.entity.Transcation;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ApiServiceImp implements ApiService {

    private final String urlIpStack = "http://api.ipstack.com/";

    private final String accessKey = "?access_key=";

    private final String apiKey = "be36842786fdd042842044b21ce293ed";

    @Autowired
    private RedisTemplate template;

    private RestTemplate restTemplate;

    public ApiServiceImp(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Transcation apiIpStack(String ipAdress) {
        Gson gson = new Gson();
        Transcation transcation = new Transcation();
        String url = urlIpStack + ipAdress + accessKey + apiKey;
        if (template.opsForValue().get(ipAdress) == null) {
            ResponseEntity<Transcation> responseEntity = this.restTemplate.getForEntity(url, Transcation.class);
            String json = gson.toJson(responseEntity.getBody());
            template.opsForValue().set(ipAdress, json);
            HttpStatus statusCode = responseEntity.getStatusCode();
            if (statusCode == HttpStatus.OK) {
                transcation = responseEntity.getBody();
            }
        } else {
            transcation = gson.fromJson(template.opsForValue().get(ipAdress).toString(), Transcation.class);
        }
        return transcation;
    }

}

