package com.nnk.springboot.unitaire.mapper;

import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.mapper.TradeMapper;
import com.nnk.springboot.model.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class TradeMapperTest {

    private TradeMapper tradeMapper;

    @BeforeEach
    public void setUp() {
        tradeMapper = Mappers.getMapper(TradeMapper.class);
    }

    @Test
    public void testToTradeDTO() {
        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Test Account");
        trade.setType("Test Type");
        trade.setBuyQuantity(100.0);

        TradeDTO tradeDTO = tradeMapper.toTradeDTO(trade);

        assertNotNull(tradeDTO);
        assertEquals(trade.getTradeId(), tradeDTO.getTradeId());
        assertEquals(trade.getAccount(), tradeDTO.getAccount());
        assertEquals(trade.getType(), tradeDTO.getType());
        assertEquals(trade.getBuyQuantity(), tradeDTO.getBuyQuantity());
    }

    @Test
    public void testToTrade() {
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setTradeId(1);
        tradeDTO.setAccount("Test Account");
        tradeDTO.setType("Test Type");
        tradeDTO.setBuyQuantity(100.0);

        Trade trade = tradeMapper.toTrade(tradeDTO);

        assertNotNull(trade);
        assertEquals(tradeDTO.getTradeId(), trade.getTradeId());
        assertEquals(tradeDTO.getAccount(), trade.getAccount());
        assertEquals(tradeDTO.getType(), trade.getType());
        assertEquals(tradeDTO.getBuyQuantity(), trade.getBuyQuantity());
    }

    @Test
    public void testUpdateTradeListFromDTO() {
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setAccount("Updated Account");
        tradeDTO.setType("Updated Type");
        tradeDTO.setBuyQuantity(200.0);

        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Old Account");
        trade.setType("Old Type");
        trade.setBuyQuantity(100.0);

        tradeMapper.updateTradeListFromDTO(tradeDTO, trade);

        assertEquals("Updated Account", trade.getAccount());
        assertEquals("Updated Type", trade.getType());
        assertEquals(200.0, trade.getBuyQuantity());
    }
}
