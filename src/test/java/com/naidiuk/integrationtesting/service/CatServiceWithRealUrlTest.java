package com.naidiuk.integrationtesting.service;

import com.naidiuk.integrationtesting.dto.CatFactsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CatServiceWithRealUrlTest {
    @Autowired
    private CatService catService;

    @Test
    void whenGetCatFact_thenReceiveCatFact() {
        //when
        CatFactsDto catFactDto = catService.getCatFact();
        String fact = catFactDto.getFact();
        int length = catFactDto.getLength();

        //then
        assertNotNull(fact);
        assertThat(fact.length()).isGreaterThan(0);
        assertThat(length).isGreaterThan(0);
    }
}
