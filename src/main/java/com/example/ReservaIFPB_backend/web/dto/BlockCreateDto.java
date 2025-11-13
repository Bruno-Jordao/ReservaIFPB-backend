package com.example.ReservaIFPB_backend.web.dto;

import com.example.ReservaIFPB_backend.entity.Campus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlockCreateDto {

    private String name;
    private Campus campus;
}
