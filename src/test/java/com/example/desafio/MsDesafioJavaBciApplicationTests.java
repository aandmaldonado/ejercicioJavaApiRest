package com.example.desafio;

import com.example.desafio.dto.PhoneDTO;
import com.example.desafio.dto.UserRequestDTO;
import com.example.desafio.dto.UserResponseDTO;
import com.example.desafio.exception.EmailFormatException;
import com.example.desafio.exception.EmailRegisteredException;
import com.example.desafio.exception.PasswordFormatException;
import com.example.desafio.model.UserModel;
import com.example.desafio.repository.UserRepository;
import com.example.desafio.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MsDesafioJavaBciApplicationTests {

    @MockBean
    UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserModel userModel;

    private UserResponseDTO responseDTO;

    private String token;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userModel = UserModel.builder()
                .id("b750edfc-9826-403c-9653-67d79544b079")
                .name("Alvaro Maldonado")
                .email("alvaro@email.com")
                .token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbHZhcm9AbWFpbC5jb20iLCJpYXQiOjE3MDA3NDM0OTgsImV4cCI6MTcwMDc0NTI5OH0.i46CsOL_bhnBfpX-YiHEDPysK3QeP1zXDSS7MqlYFP0")
                .build();
        responseDTO = UserResponseDTO.builder()
                .token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbHZhcm9AbWFpbC5jb20iLCJpYXQiOjE3MDA3NDM0OTgsImV4cCI6MTcwMDc0NTI5OH0.i46CsOL_bhnBfpX-YiHEDPysK3QeP1zXDSS7MqlYFP0")
                .lastLogin(new Date())
                .isActive(true)
                .created(new Date())
                .modified(new Date())
                .build();

    }

    @Test
    public void testCreateUsuario() {

        PhoneDTO phone = PhoneDTO.builder()
                .cityCode("23")
                .number("1")
                .countryCode("AA").build();

        UserRequestDTO request = UserRequestDTO.builder()
                .name("Alvaro Maldonado")
                .email("alvaro@email.com")
                .password("Amrrpr88")
                .phones(List.of(phone)).build();

        when(userRepository.findByEmail(request.email())).thenReturn(Optional.of(new UserModel()));
        when(userRepository.save(any(UserModel.class))).thenReturn(userModel);
        UserResponseDTO response = userService.createUser(request);

        assertThat(response).isEqualTo(responseDTO);
    }

    @Test
    public void testEmailExistente() {
        UserRequestDTO request = UserRequestDTO.builder()
                .name("Alvaro Maldonado")
                .email("alvaro@email.com")
                .password("Amrrpr88")
                .build();
        when(userRepository.findByEmail(request.email())).thenReturn(Optional.of(userModel));
        assertThrows(EmailRegisteredException.class, () -> userService.createUser(request));
    }

    @Test
    public void testEmailFormat() {
        UserRequestDTO request = UserRequestDTO.builder()
                .name("Alvaro Maldonado")
                .email("alvaroemailcom")
                .password("Amrrpr88")
                .build();

        UserModel user = new UserModel();
        when(userRepository.findByEmail(request.email())).thenReturn(Optional.of(user));
        assertThrows(EmailFormatException.class, () -> userService.createUser(request));
    }

    @Test
    public void testPasswordFormat() {
        UserRequestDTO request = UserRequestDTO.builder()
                .name("Alvaro Maldonado")
                .email("alvaroemailcom")
                .password("123456")
                .build();

        UserModel user = new UserModel();
        when(userRepository.findByEmail(request.email())).thenReturn(Optional.of(user));
        assertThrows(PasswordFormatException.class, () -> userService.createUser(request));
    }

}


