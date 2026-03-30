package org.pruebatecnica.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ValidationConfig {
    @Value("${validation.password-regex}")
    private String passwordRegex;
}
