package com.example.desafio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Date;


@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponseDTO(
        String id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "America/Santiago")
        Date created,
        @JsonProperty("last_Login")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "America/Santiago")
        Date lastLogin,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "America/Santiago")
        Date modified,
        String token,
        @JsonProperty("isactive")
        boolean isActive
) {
}
