package org.pruebatecnica.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pruebatecnica.dto.UserRequestDTO;
import org.pruebatecnica.dto.UserResponseDTO;
import org.pruebatecnica.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@Valid @RequestBody UserRequestDTO requestDTO) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(requestDTO));
    }

}
