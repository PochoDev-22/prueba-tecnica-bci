package org.pruebatecnica.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pruebatecnica.dto.PhoneRequestDTO;
import org.pruebatecnica.dto.UserRequestDTO;
import org.pruebatecnica.dto.UserResponseDTO;
import org.pruebatecnica.dto.UserSimpleResponseDTO;
import org.pruebatecnica.model.Phone;
import org.pruebatecnica.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "uuid", source = "id")
    @Mapping(target = "created", source = "createdAt")
    @Mapping(target = "modified", source = "updatedAt")
    @Mapping(target = "lastLogin", source = "lastLogin")
    @Mapping(target = "token", source = "token")
    @Mapping(target = "isActive", source = "isActive")
    UserResponseDTO toResponse(User user);

    @Mapping(target = "uuid", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "created", source = "createdAt")
    @Mapping(target = "modified", source = "updatedAt")
    @Mapping(target = "lastLogin", source = "lastLogin")
    @Mapping(target = "isActive", source = "isActive")
    UserSimpleResponseDTO toSimpleResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Phone toRequestPhone(PhoneRequestDTO request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "phones", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "mail", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toRequestUser(UserRequestDTO request);

}
