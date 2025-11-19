package com.example.ReservaIFPB_backend.web.dto;

import com.example.ReservaIFPB_backend.entity.Campus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlockResponseDto {

    private Long id;
    private String name;
    private Long campusId;
}
