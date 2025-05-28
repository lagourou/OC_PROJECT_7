package com.nnk.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.model.Trade;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TradeMapper {

    TradeDTO toTradeDTO(Trade trade);

    Trade toTrade(TradeDTO tradeDTO);

    void updateTradeListFromDTO(TradeDTO dto, @MappingTarget Trade entity);

}
