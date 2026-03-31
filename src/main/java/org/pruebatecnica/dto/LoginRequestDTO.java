package org.pruebatecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(
        name = "LoginRequestDTO",
        description = "DTO para el login del usuario"
)
public class LoginRequestDTO {
    @Schema(
            description = "Email del usuario",
            example = "Marta@rodrigue.cl",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    @Pattern(
            regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.cl$",
            message = "El correo debe tener formato aaaaaaa@dominio.cl"
    )
    private String mail;

    @Schema(
            description = "Password del usuario",
            example = "hunter2A",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private String password;
}
