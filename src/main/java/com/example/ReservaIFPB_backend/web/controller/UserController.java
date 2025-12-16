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

@Tag(name = "Users", description = "Endpoints para gerenciamento de Usuários")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Cria um novo usuário", description = "Registra um novo usuário no sistema")
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateDto dto){
        User newUser = userService.saveUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(newUser));
    }

    @Operation(summary = "Busca usuário por ID", description = "Retorna os detalhes de um usuário específico")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserId(@PathVariable Long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista completa de todos os usuários cadastrados")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(UserMapper.toListDto(users));
    }

    @Operation(summary = "Atualiza um usuário", description = "Altera os dados de um usuário existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserCreateDto dto
    ) {
        User userUpdate = userService.updateUserById(id, dto);
        return ResponseEntity.ok(UserMapper.toDto(userUpdate));
    }

    @Operation(summary = "Exclui um usuário", description = "Remove um usuário do sistema pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.excludeUser(id);
        return ResponseEntity.noContent().build();
    }
}