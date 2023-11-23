package com.example.desafio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PhoneDTO(
        String number,
        @JsonProperty("citycode")
        String cityCode,
        @JsonProperty("contrycode")
        String countryCode
) {
}
