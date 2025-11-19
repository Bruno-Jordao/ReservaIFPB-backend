package com.example.ReservaIFPB_backend.web.dto.mapper;

import com.example.ReservaIFPB_backend.entity.Block;
import com.example.ReservaIFPB_backend.entity.Campus;
import com.example.ReservaIFPB_backend.web.dto.BlockCreateDto;
import com.example.ReservaIFPB_backend.web.dto.BlockResponseDto;
import com.example.ReservaIFPB_backend.web.dto.CampusResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BlockMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Block toBlock(BlockCreateDto dto){
        Block block = new Block();
        block.setName(dto.getName());
        return block;
    }
    public static BlockResponseDto toDto(Block block){
        BlockResponseDto dto = new BlockResponseDto();
        dto.setId(block.getId());
        dto.setName(block.getName());
        dto.setCampusId(block.getCampus().getId());
        return dto;
    }

    public static List<BlockResponseDto> toListDto(List<Block> allBlocks){
        return allBlocks.stream().map(block -> toDto(block)).collect(Collectors.toList());
    }
}
