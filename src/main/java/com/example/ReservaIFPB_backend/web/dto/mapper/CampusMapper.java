package com.example.ReservaIFPB_backend.web.dto.mapper;

import com.example.ReservaIFPB_backend.entity.Campus;
import com.example.ReservaIFPB_backend.web.dto.CampusCreateDto;
import com.example.ReservaIFPB_backend.web.dto.CampusResponseDto;
import org.modelmapper.ModelMapper;

public class CampusMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Campus toCampus(CampusCreateDto createDto){
        return modelMapper.map(createDto, Campus.class);
    }

    public static CampusResponseDto toDto(Campus campus){
        return modelMapper.map(campus, CampusResponseDto.class);
    }
}
