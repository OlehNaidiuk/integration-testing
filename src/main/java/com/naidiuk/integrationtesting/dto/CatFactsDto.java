package com.naidiuk.integrationtesting.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class CatFactsDto {
    private String fact;
    private int length;
}
