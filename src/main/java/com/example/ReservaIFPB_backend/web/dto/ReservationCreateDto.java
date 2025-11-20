package com.example.ReservaIFPB_backend.web.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ReservationCreateDto {

    private Long teacherId;
    private Long roomId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private String activity;
}