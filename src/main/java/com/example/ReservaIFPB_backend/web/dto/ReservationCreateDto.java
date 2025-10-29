package com.example.ReservaIFPB_backend.web.dto;

import com.example.ReservaIFPB_backend.entity.Room;
import com.example.ReservaIFPB_backend.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ReservationCreateDto {

    private User teacher;
    private Room room;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private String activity;
}