package org.pruebatecnica.service;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.config.ValidationConfig;
import org.pruebatecnica.dto.UserRequestDTO;
import org.pruebatecnica.dto.UserResponseDTO;
import org.pruebatecnica.model.Phone;
import org.pruebatecnica.model.User;
import org.pruebatecnica.repository.UserRepository;
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

        User newUser = User.builder()
                .name(request.getName())
                .mail(request.getEmail())
                .password(request.getPassword())
                .token(UUID.randomUUID().toString())
                .isActive(true)
                .lastLogin(LocalDateTime.now())
                .build();

        List<Phone> phones = request.getPhones().stream().map(p -> {
            Phone phone = Phone.builder()
                    .number(p.getNumber())
                    .countryCode(p.getCountryCode())
                    .cityCode(p.getCityCode())
                    .build();

            phone.setUser(newUser);
            return phone;
        }).toList();

        newUser.setPhones(phones);

        User saved = userRepository.save(newUser);

        return UserResponseDTO.builder()
                .uuid(saved.getId())
                .created(saved.getCreatedAt())
                .modified(saved.getUpdatedAt())
                .lastLogin(saved.getLastLogin())
                .token(saved.getToken())
                .isActive(saved.getIsActive())
                .build();
    }
}
