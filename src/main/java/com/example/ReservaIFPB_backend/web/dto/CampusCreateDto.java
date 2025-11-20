package com.example.ReservaIFPB_backend.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CampusCreateDto {

    @NotBlank
    private String name;
    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;
}
