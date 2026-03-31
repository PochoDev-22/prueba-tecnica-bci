package org.pruebatecnica.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * JWT Util
 *
 * <p>Utilidad para la generación de tokens JWT. </p>
 *
 * @author Rodolfo Crisanto
 * @version 1.0
 */
@Component
public class JWTUtil {

    @Value("${security.jwt.key.private}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    @Value("${security.jwt.expired}")
    private String expired;

    /**
     * Genera un token JWT para un usuario.
     *
     * @param username mail del usuario
     * @param role rol del usuario
     * @return token JWT firmado
     */
    public String createToken(String username, String role) {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
        return JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(username)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(expired)))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }
}
