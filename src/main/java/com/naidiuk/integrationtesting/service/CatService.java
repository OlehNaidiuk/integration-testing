package com.naidiuk.integrationtesting.service;

import com.naidiuk.integrationtesting.dto.CatFactsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatService {
    @Value("${cat-fact.url}")
    private String url;
    private final RestTemplate restTemplate;

    public CatService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CatFactsDto getCatFact() {
        return restTemplate.getForObject(url, CatFactsDto.class);
    }
}
