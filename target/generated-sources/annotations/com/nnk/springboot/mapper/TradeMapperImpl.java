package com.nnk.springboot.mapper;

import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.model.Trade;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T20:38:22+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250526-2018, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class TradeMapperImpl implements TradeMapper {

    @Override
    public TradeDTO toTradeDTO(Trade trade) {
        if ( trade == null ) {
            return null;
        }

        TradeDTO tradeDTO = new TradeDTO();

        tradeDTO.setAccount( trade.getAccount() );
        tradeDTO.setBuyQuantity( trade.getBuyQuantity() );
        tradeDTO.setTradeId( trade.getTradeId() );
        tradeDTO.setType( trade.getType() );

        return tradeDTO;
    }

    @Override
    public Trade toTrade(TradeDTO tradeDTO) {
        if ( tradeDTO == null ) {
            return null;
        }

        Trade trade = new Trade();

        trade.setAccount( tradeDTO.getAccount() );
        trade.setBuyQuantity( tradeDTO.getBuyQuantity() );
        if ( tradeDTO.getTradeId() != null ) {
            trade.setTradeId( tradeDTO.getTradeId() );
        }
        trade.setType( tradeDTO.getType() );

        return trade;
    }

    @Override
    public void updateTradeListFromDTO(TradeDTO dto, Trade entity) {
        if ( dto == null ) {
            return;
        }

        entity.setAccount( dto.getAccount() );
        entity.setBuyQuantity( dto.getBuyQuantity() );
        if ( dto.getTradeId() != null ) {
            entity.setTradeId( dto.getTradeId() );
        }
        entity.setType( dto.getType() );
    }
}
