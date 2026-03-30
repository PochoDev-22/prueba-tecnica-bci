package org.pruebatecnica.service;

import org.pruebatecnica.dto.UserRequestDTO;
import org.pruebatecnica.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO save(UserRequestDTO request) throws Exception;
}
