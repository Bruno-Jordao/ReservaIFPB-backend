package com.example.ReservaIFPB_backend.web.controller;

import com.example.ReservaIFPB_backend.entity.User;
import com.example.ReservaIFPB_backend.service.UserService;
import com.example.ReservaIFPB_backend.web.dto.UserCreateDto;
import com.example.ReservaIFPB_backend.web.dto.UserResponseDto;
import com.example.ReservaIFPB_backend.web.dto.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateDto dto){
        User newUser = userService.saveUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(newUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserId(@PathVariable Long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(UserMapper.toListDto(users));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserCreateDto dto
    ) {
        User userUpdate = userService.updateUserById(id, dto);
        return ResponseEntity.ok(UserMapper.toDto(userUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.excludeUser(id);
        return ResponseEntity.noContent().build();
    }
}