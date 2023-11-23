package com.example.desafio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;


@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserRequestDTO(
        String name,
        String email,
        String password,
        List<PhoneDTO> phones
) {
}
