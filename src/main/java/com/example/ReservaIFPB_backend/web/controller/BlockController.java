package com.example.ReservaIFPB_backend.web.controller;

import com.example.ReservaIFPB_backend.entity.Block;
import com.example.ReservaIFPB_backend.entity.Campus;
import com.example.ReservaIFPB_backend.service.BlockService;
import com.example.ReservaIFPB_backend.web.dto.BlockCreateDto;
import com.example.ReservaIFPB_backend.web.dto.BlockResponseDto;
import com.example.ReservaIFPB_backend.web.dto.CampusResponseDto;
import com.example.ReservaIFPB_backend.web.dto.mapper.BlockMapper;
import com.example.ReservaIFPB_backend.web.dto.mapper.CampusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/blocks")
public class BlockController {

    private final BlockService blockService;

    @PostMapping
    public ResponseEntity<BlockResponseDto> createBlock(@RequestBody BlockCreateDto dto){
        Block newBlock = blockService.saveBlock(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(BlockMapper.toDto(newBlock));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlockResponseDto> getBlockId(@PathVariable Long id){
        Block block = blockService.getBlockById(id);
        return ResponseEntity.ok(BlockMapper.toDto(block));
    }

    @GetMapping
    public ResponseEntity<List<BlockResponseDto>> getAll(){
        List<Block> blocks = blockService.getAllBlocks();
        return ResponseEntity.ok(BlockMapper.toListDto(blocks));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlockResponseDto> updateBlock(
            @PathVariable Long id,
            @RequestBody BlockCreateDto dto
    ) {
        Block blockUpdate = blockService.updateBlockById(id, dto);
        return ResponseEntity.ok(BlockMapper.toDto(blockUpdate));
    }
}
