package com.example.desafio.service;

import com.example.desafio.dto.UserRequestDTO;
import com.example.desafio.dto.UserResponseDTO;
import com.example.desafio.exception.EmailFormatException;
import com.example.desafio.exception.EmailRegisteredException;
import com.example.desafio.exception.PasswordFormatException;
import com.example.desafio.model.PhoneModel;
import com.example.desafio.model.UserModel;
import com.example.desafio.repository.UserRepository;
import com.example.desafio.security.JwtProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.desafio.util.Constants.ERR_CORREO_REG;
import static com.example.desafio.util.Constants.ERR_FORMATO_CORREO;
import static com.example.desafio.util.Constants.ERR_FORMATO_PASSWORD;
import static com.example.desafio.util.Constants.PATTERN_EMAIL;

@Service
@Log4j2
public class IUserService implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder encoder;
    @Value("${validation.password.pattern}")
    private String PATTERN_PASSWORD;

    private final Function<UserRequestDTO, UserModel> initUser = userRequestDTO -> {
        return UserModel.builder()
                .id(UUID.randomUUID().toString())
                .active(true)
                .email(userRequestDTO.email())
                .created(new Date())
                .modified(new Date())
                .lastLogin(new Date())
                .name(encoder.encode(userRequestDTO.name()))
                .password(userRequestDTO.password())
                .build();
    };

    private final BiConsumer<UserModel, UserRequestDTO> addPhones = (userModel, userRequestDTO) -> {
        userModel.setPhones(userRequestDTO.phones().stream()
                .map(phone -> {
                    return PhoneModel.builder()
                            .user(userModel)
                            .countryCode(phone.countryCode())
                            .number(phone.number())
                            .cityCode(phone.cityCode())
                            .build();
                })
                .collect(Collectors.toList()));
    };

    private final Consumer<UserModel> addToken = userModel ->
            userModel.setToken(jwtProvider.generateToken(userModel.getEmail()));

    private final Consumer<UserRequestDTO> validateFormatFields = req -> {
        if (!req.email().matches(PATTERN_EMAIL))
            throw new EmailFormatException(ERR_FORMATO_CORREO);
        if (!req.password().matches(PATTERN_PASSWORD))
            throw new PasswordFormatException(ERR_FORMATO_PASSWORD);
    };

    private final Consumer<String> validateEmailExists = email -> {
        if (userRepository.existsByEmail(email))
            throw new EmailRegisteredException(ERR_CORREO_REG);
    };

    private final Function<UserModel, UserResponseDTO> wrapperUser = userModel ->
            UserResponseDTO.builder()
                    .token(userModel.getToken())
                    .id(userModel.getId())
                    .created(userModel.getCreated())
                    .isActive(userModel.isActive())
                    .lastLogin(userModel.getLastLogin())
                    .modified(userModel.getModified())
                    .build();

    @Override
    public UserResponseDTO createUser(UserRequestDTO req) {
        validateEmailExists.accept(req.email());
        validateFormatFields.accept(req);
        UserModel userModel = initUser.apply(req);
        addPhones.accept(userModel, req);
        addToken.accept(userModel);
        userModel = userRepository.save(userModel);
        return wrapperUser.apply(userModel);
    }
}
