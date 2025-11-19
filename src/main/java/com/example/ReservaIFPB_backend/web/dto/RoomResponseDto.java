package com.example.ReservaIFPB_backend.web.dto;

import com.example.ReservaIFPB_backend.entity.Block;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomResponseDto {

    private Long id;
    private String name;
    private int capacity;
    private Long blockId;
    private String floor;
}
