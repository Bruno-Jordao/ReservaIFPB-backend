package com.example.ReservaIFPB_backend.web.dto.mapper;

import com.example.ReservaIFPB_backend.entity.Block;
import com.example.ReservaIFPB_backend.entity.Room;
import com.example.ReservaIFPB_backend.web.dto.RoomCreateDto;
import com.example.ReservaIFPB_backend.web.dto.RoomResponseDto;
import org.modelmapper.ModelMapper;

public class RoomMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Room toRoom(RoomCreateDto dto, Block block){
        Room room = new Room();
        room.setName(dto.getName());
        room.setCapacity(dto.getCapacity());
        room.setFloor(dto.getFloor());
        room.setBlock(block);
        return room;
    }
    public static RoomResponseDto toDto(Room room){
        RoomResponseDto dto = new RoomResponseDto();
        dto.setId(room.getId());
        dto.setName(room.getName());
        dto.setCapacity(room.getCapacity());
        dto.setBlockId(room.getBlock().getId());
        dto.setFloor(room.getFloor());
        return dto;
    }
}
