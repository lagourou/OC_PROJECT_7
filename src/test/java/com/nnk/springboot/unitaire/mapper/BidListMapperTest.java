package com.nnk.springboot.unitaire.mapper;

import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.mapper.BidListMapper;
import com.nnk.springboot.model.BidList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BidListMapperTest {

    private BidListMapper bidListMapper;

    @BeforeEach
    public void setUp() {
        bidListMapper = Mappers.getMapper(BidListMapper.class);
    }

    @Test
    public void testTobidListDTO() {
        BidList bidList = new BidList();
        bidList.setBidListId(1);
        bidList.setAccount("Test Account");
        bidList.setType("Test Type");
        bidList.setBidQuantity(BigDecimal.valueOf(100.0));

        BidListDTO bidListDTO = bidListMapper.tobidListDTO(bidList);

        assertNotNull(bidListDTO);
        assertEquals(bidList.getBidListId(), bidListDTO.getBidListId());
        assertEquals(bidList.getAccount(), bidListDTO.getAccount());
        assertEquals(bidList.getType(), bidListDTO.getType());
    }

    @Test
    public void testTobidList() {
        BidListDTO bidListDTO = new BidListDTO();
        bidListDTO.setBidListId(1);
        bidListDTO.setAccount("Test Account");
        bidListDTO.setType("Test Type");

        BidList bidList = bidListMapper.tobidList(bidListDTO);

        assertNotNull(bidList);
        assertEquals(bidListDTO.getBidListId(), bidList.getBidListId());
        assertEquals(bidListDTO.getAccount(), bidList.getAccount());
        assertEquals(bidListDTO.getType(), bidList.getType());
    }

    @Test
    public void testUpdateBidListFromDTO() {
        BidListDTO bidListDTO = new BidListDTO();
        bidListDTO.setAccount("Updated Account");
        bidListDTO.setType("Updated Type");

        BidList bidList = new BidList();
        bidList.setBidListId(1);
        bidList.setAccount("Old Account");
        bidList.setType("Old Type");

        bidListMapper.updateBidListFromDTO(bidListDTO, bidList);

        assertEquals("Updated Account", bidList.getAccount());
        assertEquals("Updated Type", bidList.getType());
    }
}
