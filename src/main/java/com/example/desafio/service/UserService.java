package com.example.desafio.service;

import com.example.desafio.dto.UserRequestDTO;
import com.example.desafio.dto.UserResponseDTO;

public interface UserService {

    public UserResponseDTO createUser(UserRequestDTO req);

}
