package com.example.desafio.dto;

import lombok.Builder;

@Builder
public record ErrorDTO(
        String mensaje
) {
}
