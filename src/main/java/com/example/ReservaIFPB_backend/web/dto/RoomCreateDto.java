package com.example.ReservaIFPB_backend.web.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class RoomCreateDto {

    private String name;
    private int capacity;
    private Long blockId;
    private String floor;
}
