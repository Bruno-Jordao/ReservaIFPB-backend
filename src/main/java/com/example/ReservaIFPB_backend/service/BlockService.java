package com.example.ReservaIFPB_backend.service;

import com.example.ReservaIFPB_backend.entity.Block;
import com.example.ReservaIFPB_backend.entity.Campus;
import com.example.ReservaIFPB_backend.repository.BlockRepository;
import com.example.ReservaIFPB_backend.web.dto.BlockCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlockService {

    private final BlockRepository blockRepository;
    private final CampusService campusService;

    @Transactional
    public Block saveBlock(BlockCreateDto dto){
        Campus campus = campusService.getCampusById(dto.getCampusId());

        Block block = new Block();
        block.setName(dto.getName());
        block.setCampus(campus);

        return blockRepository.save(block);
    }

    @Transactional(readOnly = true)
    public Block getBlockById(Long id){
        return blockRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Block not found!")
        );
    }

    @Transactional(readOnly = true)
    public List<Block> getAllBlocks(){
        return blockRepository.findAll();
    }
}
