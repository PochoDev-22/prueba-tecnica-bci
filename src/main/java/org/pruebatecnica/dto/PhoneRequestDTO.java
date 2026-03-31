package org.pruebatecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "PhoneRequestDTO",
        description = "DTO para el registro de telefonos del usuario"
)
public class PhoneRequestDTO {
    @Schema(
            description = "Numero de telefono",
            example = "1234567"
    )
    private String number;
    @Schema(
            description = "Codigo de ciudad",
            example = "1"
    )
    private String cityCode;
    @Schema(
            description = "Codigo de pais",
            example = "56"
    )
    private String countryCode;
}
