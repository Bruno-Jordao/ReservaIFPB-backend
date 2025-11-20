package com.example.ReservaIFPB_backend.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class RoomCreateDto {

    @NotBlank
    private String name;
    @NotNull
    @Positive
    private Integer capacity;
    @NotNull
    private Long blockId;
    @NotBlank
    private String floor;
}
