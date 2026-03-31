package org.pruebatecnica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pruebatecnica.dto.*;
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
@Tag(
        name = "Usuarios",
        description = "Endpoints para la gestion de usuarios"
)
public class UserController {
    private final UserService userService;

    /**
     * Registrar un nuevo usuario
     * @param requestDTO
     * @return {@link UserResponseDTO}
     * @throws Exception
     */
    @Operation(
            summary = "Registrar usuario",
            description = "Registra un usuario y retorna sus datos registreados"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Creacion existosa del usuario",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))
            )
    })
    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@Valid @RequestBody UserRequestDTO requestDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(requestDTO));
    }

    /**
     * Muestra la lista de usuarios registrados
     *
     * @return lista {@link UserSimpleResponseDTO}
     */
    @Operation(
            summary = "Obtener a los usuarios registrados",
            description = "Devuelve una lista de usuarios registrados"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtencion de usuarios exitosa",
                    content = @Content(schema = @Schema(implementation = UserSimpleResponseDTO[].class))
            )
    })
    @GetMapping
    public ResponseEntity<List<UserSimpleResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    /**
     * Registra el login para actualizar el atributo last login del usuario
     * @param requestDTO
     * @return {@link LoginResponseDTO}
     * @throws Exception
     */

    @Operation(
            summary = "Login del usuario",
            description = "Permite loguear al usuario para actualizar el last login"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login realizado con exito",
                    content = @Content(schema = @Schema(implementation = LoginResponseDTO.class))
            )
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO requestDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(requestDTO));
    }

}
