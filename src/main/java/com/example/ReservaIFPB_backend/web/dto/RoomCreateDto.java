package com.example.ReservaIFPB_backend.web.dto;

import com.example.ReservaIFPB_backend.entity.Block;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class RoomCreateDto {

    private String name;
    private int capacity;
    private Block block;
    private String floor;
}
