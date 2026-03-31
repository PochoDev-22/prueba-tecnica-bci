package org.pruebatecnica.service;

import org.pruebatecnica.dto.*;

import java.util.List;

/**
 * User Service Interface.
 *
 * <p>Define las operaciones de gestion de usuarios </p>
 *
 * @author Rodolfo Crisanto
 * @version 1.0
 */
public interface UserService {
    UserResponseDTO save(UserRequestDTO request) throws Exception;
    List<UserSimpleResponseDTO> getAll();
    LoginResponseDTO login(LoginRequestDTO requestDTO) throws Exception;
}
