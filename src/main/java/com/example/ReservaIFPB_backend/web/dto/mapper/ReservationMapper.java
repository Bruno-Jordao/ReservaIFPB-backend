package com.example.ReservaIFPB_backend.web.dto.mapper;

import com.example.ReservaIFPB_backend.entity.Reservation;
import com.example.ReservaIFPB_backend.web.dto.ReservationCreateDto;
import com.example.ReservaIFPB_backend.web.dto.ReservationResponseDto;
import org.modelmapper.ModelMapper;

public class ReservationMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Reservation toReservation(ReservationCreateDto createDto){
        return modelMapper.map(createDto, Reservation.class);
    }

    public static ReservationResponseDto toDto(Reservation reservation){
        return modelMapper.map(reservation, ReservationResponseDto.class);
    }
}