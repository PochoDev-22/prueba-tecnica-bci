package org.pruebatecnica.service;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.config.ValidationConfig;
import org.pruebatecnica.dto.UserRequestDTO;
import org.pruebatecnica.dto.UserResponseDTO;
import org.pruebatecnica.mapper.UserMapper;
import org.pruebatecnica.model.Phone;
import org.pruebatecnica.model.User;
import org.pruebatecnica.repository.UserRepository;
import org.pruebatecnica.util.JWTUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ValidationConfig validationConfig;
    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;

    @Override
    public UserResponseDTO save(UserRequestDTO request) throws Exception{

        // validate exist mail
        boolean existMail = userRepository.existsByMail(request.getEmail());

        // validate password format
        if(!Pattern.matches(validationConfig.getPasswordRegex(), request.getPassword()))
        {
            throw new Exception("La contraseña no cumple el formato requerido");
        }

        if(existMail) {
            throw new Exception("El correo ya esta registrado en la base de datos.");
        }

        User newUser = userMapper.toRequestUser(request);

        List<Phone> phones = request.getPhones().stream().map(p -> {
            Phone phone = userMapper.toRequestPhone(p);
            phone.setUser(newUser);
            return phone;
        }).toList();

        newUser.setPhones(phones);
        newUser.setToken(jwtUtil.createToken(request.getEmail(), "ADMIN"));
        newUser.setLastLogin(LocalDateTime.now());
        newUser.setIsActive(true);

        User saved = userRepository.save(newUser);

        return userMapper.toResponse(saved);
    }
}
