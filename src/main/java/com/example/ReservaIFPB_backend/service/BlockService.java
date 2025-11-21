package com.example.ReservaIFPB_backend.service;

import com.example.ReservaIFPB_backend.entity.Block;
import com.example.ReservaIFPB_backend.entity.Campus;
import com.example.ReservaIFPB_backend.exception.BlockAlreadyExistsException;
import com.example.ReservaIFPB_backend.exception.BlockNotFoundException;
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

        boolean exists = blockRepository.existsByNameIgnoreCaseAndCampusId(dto.getName(), dto.getCampusId());
        if (exists) {
            throw new BlockAlreadyExistsException("Block already exists in this campus");
        }

        Block block = new Block();
        block.setName(dto.getName());
        block.setCampus(campus);

        return blockRepository.save(block);
    }

    @Transactional(readOnly = true)
    public Block getBlockById(Long id){
        return blockRepository.findById(id).orElseThrow(
                () -> new BlockNotFoundException("Block not found with id: " + id)
        );
    }

    @Transactional(readOnly = true)
    public List<Block> getAllBlocks(){
        return blockRepository.findAll();
    }

    @Transactional
    public Block updateBlockById(Long id, BlockCreateDto dto) {
        Block block = getBlockById(id);

        Campus campus = campusService.getCampusById(dto.getCampusId());

        block.setName(dto.getName());
        block.setCampus(campus);

        return block;
    }

    @Transactional
    public void excludeBlock(Long id){
        Block block = getBlockById(id);
        blockRepository.deleteById(id);
    }
}
