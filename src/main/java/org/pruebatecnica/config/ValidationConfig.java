package org.pruebatecnica.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Validation Config
 *
 * <p>Clase de configuracion que permite obtener los parametros que debe cumplir la password - REGEX </p>
 *
 * @author Rodolfo Crisanto
 * @version 1.0
 */
@Component
@Getter
public class ValidationConfig {
    @Value("${validation.password-regex}")
    private String passwordRegex;
}
