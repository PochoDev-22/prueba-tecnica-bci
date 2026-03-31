package org.pruebatecnica.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pruebatecnica.config.ValidationConfig;
import org.pruebatecnica.dto.PhoneRequestDTO;
import org.pruebatecnica.dto.UserRequestDTO;
import org.pruebatecnica.dto.UserResponseDTO;
import org.pruebatecnica.mapper.UserMapper;
import org.pruebatecnica.model.Phone;
import org.pruebatecnica.model.User;
import org.pruebatecnica.repository.UserRepository;
import org.pruebatecnica.util.JWTUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ValidationConfig validationConfig;

    @Mock
    private UserMapper userMapper;

    @Mock
    private JWTUtil jwtUtil;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldCreateUserSuccessfully() throws Exception {
        PhoneRequestDTO phoneRequestDTO = new PhoneRequestDTO();
        phoneRequestDTO.setNumber("1234567");
        phoneRequestDTO.setCityCode("1");
        phoneRequestDTO.setCountryCode("57");

        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("Juan");
        requestDTO.setEmail("juan@bci.cl");
        requestDTO.setPassword("hunter2A");
        requestDTO.setPhones(List.of(phoneRequestDTO));

        when(userRepository.existsByMail(requestDTO.getEmail())).thenReturn(false);
        when(validationConfig.getPasswordRegex()).thenReturn(".*");
        when(jwtUtil.createToken(requestDTO.getEmail(), "ADMIN")).thenReturn("JwtTest");

        User user = new User();
        when(userMapper.toRequestUser(requestDTO)).thenReturn(user);
        when(userMapper.toRequestPhone(phoneRequestDTO)).thenReturn(new Phone());
        when(userRepository.save(any())).thenReturn(user);
        when(userMapper.toResponse(user)).thenReturn(UserResponseDTO.builder().build());
        var response = userService.save(requestDTO);

        assertNotNull(response);
    }

    @Test
    void shouldFailWhenEmailAlreadyExists() {

        UserRequestDTO request = new UserRequestDTO();
        request.setEmail("marcelo@bci.cl");
        request.setPassword("hunter2A");

        when(userRepository.existsByMail(request.getEmail())).thenReturn(true);
        when(validationConfig.getPasswordRegex()).thenReturn(".*");

        Exception ex = assertThrows(Exception.class, () -> {
            userService.save(request);
        });

        assertEquals("El correo ya esta registrado en la base de datos.", ex.getMessage());
    }


}
