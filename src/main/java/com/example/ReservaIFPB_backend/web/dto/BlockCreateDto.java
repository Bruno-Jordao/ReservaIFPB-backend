package com.example.ReservaIFPB_backend.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlockCreateDto {

    @NotBlank
    private String name;
    @NotNull
    private Long campusId;
}
