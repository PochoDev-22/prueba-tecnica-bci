package org.pruebatecnica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    @Pattern(
            regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.cl$",
            message = "El correo debe tener formato aaaaaaa@dominio.cl"
    )
    private String email;

    @NotBlank
    private String password;
    private List<PhoneRequestDTO> phones;
}
