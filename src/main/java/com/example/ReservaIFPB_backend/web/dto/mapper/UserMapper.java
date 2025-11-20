package com.example.ReservaIFPB_backend.web.dto.mapper;

import com.example.ReservaIFPB_backend.entity.User;
import com.example.ReservaIFPB_backend.web.dto.UserCreateDto;
import com.example.ReservaIFPB_backend.web.dto.UserResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List; // Adicionado
import java.util.stream.Collectors; // Adicionado

public class UserMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static User toUser(UserCreateDto createDto){
        return modelMapper.map(createDto, User.class);
    }

    public static UserResponseDto toDto(User user){
        return modelMapper.map(user, UserResponseDto.class);
    }

    public static List<UserResponseDto> toListDto(List<User> allUsers){
        return allUsers.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}