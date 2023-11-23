package com.example.desafio.controller;

import com.example.desafio.dto.ErrorDTO;
import com.example.desafio.dto.UserRequestDTO;
import com.example.desafio.dto.UserResponseDTO;
import com.example.desafio.repository.UserRepository;
import com.example.desafio.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Tutorial", description = "Tutorial Desafio Java BCI")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    UserService userService;
    UserRepository userRepository;

    @Operation(summary = "Registro de usuario",
            responses = {
                    @ApiResponse(responseCode = "400", description = "bad input",
                            content = {@Content(schema = @Schema(implementation = ErrorDTO.class))}),
                    @ApiResponse(responseCode = "500", description = "Error de sistema",
                            content = {@Content(schema = @Schema(implementation = ErrorDTO.class))}),
                    @ApiResponse(responseCode = "409", description = "Error correo ya registrado",
                            content = {@Content(schema = @Schema(implementation = ErrorDTO.class))}),
                    @ApiResponse(responseCode = "201", description = "Usuario registrado con exito"),
            })
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO req) {
        UserResponseDTO response = userService.createUser(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
