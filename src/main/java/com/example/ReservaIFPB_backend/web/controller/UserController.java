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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Users", description = "Endpoints for User Management")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Create a new user", description = "Register a new user in the system")
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateDto dto){
        User newUser = userService.saveUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(newUser));
    }

    @Operation(summary = "Search user by ID", description = "Returns the details of a specific user")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserId(@PathVariable Long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @Operation(summary = "List all users", description = "Returns a complete list of all registered users")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(UserMapper.toListDto(users));
    }

    @Operation(summary = "Update a user", description = "Changes the data of an existing user by ID")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserCreateDto dto
    ) {
        User userUpdate = userService.updateUserById(id, dto);
        return ResponseEntity.ok(UserMapper.toDto(userUpdate));
    }

    @Operation(summary = "Deletes a user", description = "Remove a user from the system by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.excludeUser(id);
        return ResponseEntity.noContent().build();
    }
}