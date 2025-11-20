package com.example.ReservaIFPB_backend.web.dto.mapper;

import com.example.ReservaIFPB_backend.entity.Reservation;
import com.example.ReservaIFPB_backend.web.dto.ReservationCreateDto;
import com.example.ReservaIFPB_backend.web.dto.ReservationResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ReservationResponseDto toDto(Reservation reservation){
        return modelMapper.map(reservation, ReservationResponseDto.class);
    }

    public static List<ReservationResponseDto> toListDto(List<Reservation> allReservations){
        return allReservations.stream().map(reservation -> toDto(reservation)).collect(Collectors.toList());
    }
}