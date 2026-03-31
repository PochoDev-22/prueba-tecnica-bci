package org.pruebatecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
@Schema(
        name = "UserRequestDTO",
        description = "DTO para el registro de usuario"
)
public class UserRequestDTO {
    @Schema(
            description = "Nombre del usuario",
            example = "Marta",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private String name;

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
    private String email;

    @Schema(
            description = "Password del usuario",
            example = "hunter2A",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private String password;

    @Schema(
            description = "Lista de telefonos del usuario"
    )
    private List<PhoneRequestDTO> phones;
}
