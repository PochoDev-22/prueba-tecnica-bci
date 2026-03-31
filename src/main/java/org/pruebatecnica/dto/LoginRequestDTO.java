package org.pruebatecnica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDTO {
    @NotBlank
    @Pattern(
            regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.cl$",
            message = "El correo debe tener formato aaaaaaa@dominio.cl"
    )
    private String mail;
    @NotBlank
    private String password;
}
