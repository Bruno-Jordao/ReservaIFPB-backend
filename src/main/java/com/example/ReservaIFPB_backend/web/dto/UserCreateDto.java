package com.example.ReservaIFPB_backend.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String registration;

    @NotBlank
    @Email(message = "O email institucional deve ser válido")
    private String email;

    @NotBlank
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String password;
}