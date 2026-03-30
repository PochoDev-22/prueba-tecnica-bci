package org.pruebatecnica.service;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.dto.UserRequestDTO;
import org.pruebatecnica.dto.UserResponseDTO;
import org.pruebatecnica.model.Phone;
import org.pruebatecnica.model.User;
import org.pruebatecnica.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO save(UserRequestDTO request) throws Exception{

        // validate exist mail
        boolean existMail = userRepository.existsByMail(request.getEmail());

        if(existMail) {
            throw new Exception("El correo ya esta registrado en la base de datos.");
        }

        List<Phone> phones = request.getPhones().stream().map(p -> Phone.builder()
                .number(p.getNumber())
                .countryCode(p.getCountryCode())
                .cityCode(p.getCityCode())
                .build()).toList();

        User newUser = User.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .mail(request.getEmail())
                .password(request.getPassword())
                .phones(phones)
                .build();

        User saved = userRepository.save(newUser);

        return UserResponseDTO.builder()
                .uuid(saved.getId())
                .created(saved.getCreatedAt())
                .modified(saved.getUpdatedAt())
                .lastLogin(saved.getUpdatedAt())
                .token("test")
                .isActive(true)
                .build();
    }
}
