package com.example.ReservaIFPB_backend.web.controller;

import com.example.ReservaIFPB_backend.entity.Block;
import com.example.ReservaIFPB_backend.entity.Campus;
import com.example.ReservaIFPB_backend.service.BlockService;
import com.example.ReservaIFPB_backend.web.dto.BlockCreateDto;
import com.example.ReservaIFPB_backend.web.dto.BlockResponseDto;
import com.example.ReservaIFPB_backend.web.dto.CampusResponseDto;
import com.example.ReservaIFPB_backend.web.dto.mapper.BlockMapper;
import com.example.ReservaIFPB_backend.web.dto.mapper.CampusMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Blocks", description = "Endpoints for Block Management")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/blocks")
public class BlockController {

    private final BlockService blockService;

    @Operation(summary = "Creates a new block", description = "Registers a new building belonging to a campus")
    @PostMapping
    public ResponseEntity<BlockResponseDto> createBlock(@Valid @RequestBody BlockCreateDto dto){
        Block newBlock = blockService.saveBlock(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(BlockMapper.toDto(newBlock));
    }

    @Operation(summary = "Search block by ID", description = "Returns the details of a specific block")
    @GetMapping("/{id}")
    public ResponseEntity<BlockResponseDto> getBlockId(@PathVariable Long id){
        Block block = blockService.getBlockById(id);
        return ResponseEntity.ok(BlockMapper.toDto(block));
    }

    @Operation(summary = "List all blocks", description = "Returns a complete list of all registered blocks")
    @GetMapping
    public ResponseEntity<List<BlockResponseDto>> getAll(){
        List<Block> blocks = blockService.getAllBlocks();
        return ResponseEntity.ok(BlockMapper.toListDto(blocks));
    }

    @Operation(summary = "Update a block", description = "Modifies the data of an existing block by ID")
    @PutMapping("/{id}")
    public ResponseEntity<BlockResponseDto> updateBlock(
            @PathVariable Long id,
            @Valid @RequestBody BlockCreateDto dto
    ) {
        Block blockUpdate = blockService.updateBlockById(id, dto);
        return ResponseEntity.ok(BlockMapper.toDto(blockUpdate));
    }

    @Operation(summary = "Deletes a block", description = "Remove a block from the system by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable Long id){
        blockService.excludeBlock(id);
        return ResponseEntity.noContent().build();
    }
}