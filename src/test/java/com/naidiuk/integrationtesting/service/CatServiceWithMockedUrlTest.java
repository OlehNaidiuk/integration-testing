package com.naidiuk.integrationtesting.service;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.naidiuk.integrationtesting.dto.CatFactsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@WireMockTest(httpPort = 8080)
@TestPropertySource(properties = "cat-fact.url=http://localhost:8080/fact")
class CatServiceWithMockedUrlTest {
    @Autowired
    private CatService catService;

    @Test
    void whenGetCatFact_thenReceiveCatFact(WireMockRuntimeInfo wmRuntimeInfo) {
        //prepare
        CatFactsDto catFactDto = CatFactsDto.builder()
                                            .fact("Cat is fat")
                                            .length(10)
                                            .build();
        stubFor(get("/fact").willReturn(jsonResponse(catFactDto, HttpStatus.OK.value())));

        //when
        CatFactsDto mockedCatFact = catService.getCatFact();
        String fact = mockedCatFact.getFact();
        int length = mockedCatFact.getLength();

        //then
        assertNotNull(fact);
        assertThat(fact).isEqualTo("Cat is fat");
        assertThat(length).isEqualTo(10);
    }
}