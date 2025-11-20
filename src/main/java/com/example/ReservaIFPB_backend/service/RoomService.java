package com.example.ReservaIFPB_backend.service;

import com.example.ReservaIFPB_backend.entity.Block;
import com.example.ReservaIFPB_backend.entity.Room;
import com.example.ReservaIFPB_backend.repository.RoomRepository;
import com.example.ReservaIFPB_backend.web.dto.RoomCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public Room getRoomById(Long id){
        return roomRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Room not found!")
        );
    }

    @Transactional(readOnly = true)
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    @Transactional
    public Room updateRoomById(Long id, RoomCreateDto dto) {
        Room room = getRoomById(id);

        Block block = blockService.getBlockById(dto.getBlockId());

        room.setName(dto.getName());
        room.setCapacity(dto.getCapacity());
        room.setBlock(block);
        room.setFloor(dto.getFloor());

        return room;
    }

    @Transactional
    public void excludeRoom(Long id){
        Room room = getRoomById(id);
        roomRepository.deleteById(id);
    }
}
