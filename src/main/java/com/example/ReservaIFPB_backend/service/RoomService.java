package com.example.ReservaIFPB_backend.service;

import com.example.ReservaIFPB_backend.entity.Block;
import com.example.ReservaIFPB_backend.entity.Room;
import com.example.ReservaIFPB_backend.repository.RoomRepository;
import com.example.ReservaIFPB_backend.web.dto.RoomCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final BlockService blockService;

    @Transactional
    public Room saveRoom(RoomCreateDto dto){
        Block block = blockService.getBlockById(dto.getBlockId());

        Room room = new Room();
        room.setName(dto.getName());
        room.setCapacity(dto.getCapacity());
        room.setBlock(block);
        room.setFloor(dto.getFloor());

        return roomRepository.save(room);
    }
}
