package com.example.ReservaIFPB_backend.web.dto;

import com.example.ReservaIFPB_backend.entity.Campus;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class RoomCreateDto {

    private String name;
    private int capacity;
    private String block;
    private String floor;
    private Campus campus;
}
