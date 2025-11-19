package com.example.ReservaIFPB_backend.web.controller;

import com.example.ReservaIFPB_backend.entity.Block;
import com.example.ReservaIFPB_backend.service.BlockService;
import com.example.ReservaIFPB_backend.web.dto.BlockCreateDto;
import com.example.ReservaIFPB_backend.web.dto.BlockResponseDto;
import com.example.ReservaIFPB_backend.web.dto.mapper.BlockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
