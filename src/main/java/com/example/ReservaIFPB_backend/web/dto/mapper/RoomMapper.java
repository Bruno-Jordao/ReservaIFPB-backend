package com.example.ReservaIFPB_backend.web.dto.mapper;

import com.example.ReservaIFPB_backend.entity.Room;
import com.example.ReservaIFPB_backend.web.dto.RoomCreateDto;
import com.example.ReservaIFPB_backend.web.dto.RoomResponseDto;
import org.modelmapper.ModelMapper;

public class RoomMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Room toRoom(RoomCreateDto createDto){
        return modelMapper.map(createDto, Room.class);
    }

    public static RoomResponseDto toDto(Room room){
        return modelMapper.map(room, RoomResponseDto.class);
    }
}
