package com.example.ReservaIFPB_backend.web.dto.mapper;

import com.example.ReservaIFPB_backend.entity.Block;
import com.example.ReservaIFPB_backend.web.dto.BlockCreateDto;
import com.example.ReservaIFPB_backend.web.dto.BlockResponseDto;
import org.modelmapper.ModelMapper;

public class BlockMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    //public static Block toBlock(BlockCreateDto createDto){
    //    return modelMapper.map(createDto, Block.class);
    //}

    //public static BlockResponseDto toDto(Block block){
    //    return modelMapper.map(block, BlockResponseDto.class);
    //}
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
}
