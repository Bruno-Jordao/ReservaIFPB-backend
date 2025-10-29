package com.example.ReservaIFPB_backend.web.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationResponseDto {

    private Long id;
    private UserResponseDto teacher;
    private RoomResponseDto room;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private String activity;
}