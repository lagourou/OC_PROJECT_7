package com.nnk.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.model.BidList;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BidListMapper {

    BidListDTO tobidListDTO(BidList bidList);

    BidList tobidList(BidListDTO bidListDTO);

    void updateBidListFromDTO(BidListDTO dto, @MappingTarget BidList entity);

}
