package org.pruebatecnica.service;

import org.pruebatecnica.dto.UserRequestDTO;
import org.pruebatecnica.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO save(UserRequestDTO request) throws Exception;
    List<UserResponseDTO> getAll();
}
