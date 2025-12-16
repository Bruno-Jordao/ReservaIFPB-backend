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

@Tag(name = "Blocks", description = "Endpoints para gerenciamento de Blocos")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/blocks")
public class BlockController {

    private final BlockService blockService;

    @Operation(summary = "Cria um novo bloco", description = "Registra um novo bloco pertencente a um campus")
    @PostMapping
    public ResponseEntity<BlockResponseDto> createBlock(@Valid @RequestBody BlockCreateDto dto){
        Block newBlock = blockService.saveBlock(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(BlockMapper.toDto(newBlock));
    }

    @Operation(summary = "Busca bloco por ID", description = "Retorna os detalhes de um bloco específico")
    @GetMapping("/{id}")
    public ResponseEntity<BlockResponseDto> getBlockId(@PathVariable Long id){
        Block block = blockService.getBlockById(id);
        return ResponseEntity.ok(BlockMapper.toDto(block));
    }

    @Operation(summary = "Lista todos os blocos", description = "Retorna uma lista completa de todos os blocos cadastrados")
    @GetMapping
    public ResponseEntity<List<BlockResponseDto>> getAll(){
        List<Block> blocks = blockService.getAllBlocks();
        return ResponseEntity.ok(BlockMapper.toListDto(blocks));
    }

    @Operation(summary = "Atualiza um bloco", description = "Altera os dados de um bloco existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<BlockResponseDto> updateBlock(
            @PathVariable Long id,
            @Valid @RequestBody BlockCreateDto dto
    ) {
        Block blockUpdate = blockService.updateBlockById(id, dto);
        return ResponseEntity.ok(BlockMapper.toDto(blockUpdate));
    }

    @Operation(summary = "Exclui um bloco", description = "Remove um bloco do sistema pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable Long id){
        blockService.excludeBlock(id);
        return ResponseEntity.noContent().build();
    }
}