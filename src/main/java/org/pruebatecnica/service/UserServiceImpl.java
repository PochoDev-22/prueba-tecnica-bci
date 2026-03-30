package org.pruebatecnica.service;

import org.pruebatecnica.dto.UserRequestDTO;
import org.pruebatecnica.dto.UserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public UserResponseDTO save(UserRequestDTO request) {

        // validate exist mail
        return null;
    }
}
