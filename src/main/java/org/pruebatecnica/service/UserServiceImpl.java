package org.pruebatecnica.service;

import lombok.RequiredArgsConstructor;
import org.pruebatecnica.config.ValidationConfig;
import org.pruebatecnica.dto.LoginRequestDTO;
import org.pruebatecnica.dto.LoginResponseDTO;
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

/**
 * User Service Implement.
 *
 * <p>Servicio encargado de la lógica de negocio de usuarios. </p>
 *
 * @author Rodolfo Crisanto
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ValidationConfig validationConfig;
    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;

    /**
     * Registra un nuevo usuario validando reglas de negocio
     *
     * @param request
     * @return {@link UserResponseDTO}
     * @throws Exception
     */
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

    /**
     * Retorna una lista de usuarios registrados
     *
     * @return list {@link UserResponseDTO}
     */
    @Override
    public List<UserResponseDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toResponse).toList();
    }


    /**
     * Registra el login del usuario usando el atributo last login
     *
     * @param requestDTO
     * @return {@link LoginResponseDTO}
     * @throws Exception
     */
    @Override
    public LoginResponseDTO login(LoginRequestDTO requestDTO) throws Exception{
        boolean exists = userRepository.existsByMailAndPassword(requestDTO.getMail(), requestDTO.getPassword());
        if(!exists) {
            throw new Exception("Credenciales incorrectas");
        }

        User user = userRepository.findByMailAndPassword(requestDTO.getMail(), requestDTO.getPassword());
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        return LoginResponseDTO.builder()
                .message("Login correcto, last login actualizado")
                .build();
    }
}
