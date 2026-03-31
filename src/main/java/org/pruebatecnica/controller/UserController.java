package org.pruebatecnica.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.pruebatecnica.dto.LoginRequestDTO;
import org.pruebatecnica.dto.LoginResponseDTO;
import org.pruebatecnica.dto.UserRequestDTO;
import org.pruebatecnica.dto.UserResponseDTO;
import org.pruebatecnica.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User REST controller.
 *
 * <p>Controlador encargado de la gestion de usuarios </p>
 *
 * @author Rodolfo Crisanto
 * @version 1.0
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Registrar un nuevo usuario
     * @param requestDTO
     * @return {@link UserResponseDTO}
     * @throws Exception
     */
    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@Valid @RequestBody UserRequestDTO requestDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(requestDTO));
    }

    /**
     * Muestra la lista de usuarios registrados
     *
     * @return lista {@link UserResponseDTO}
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    /**
     * Registra el login para actualizar el atributo last login del usuario
     * @param requestDTO
     * @return {@link LoginResponseDTO}
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO requestDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(requestDTO));
    }

}
