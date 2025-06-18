package com.example.deliverymatch.mappers;


import com.example.deliverymatch.dtos.UserDTO;
import com.example.deliverymatch.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO dto);
    UserDTO toDTO(User user);
    List<UserDTO> toDTOs(List<User> users);
}
