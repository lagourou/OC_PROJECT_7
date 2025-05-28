package com.nnk.springboot.unitaire.service;

import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.mapper.BidListMapper;
import com.nnk.springboot.model.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BidListServiceTest {

    @Mock
    private BidListRepository bidListRepository;

    @Mock
    private BidListMapper bidListMapper;

    @InjectMocks
    private BidListService bidListService;

    private BidList bidList;
    private BidListDTO bidListDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        bidList = new BidList();
        bidList.setBidListId(1);
        bidList.setAccount("Test Account");
        bidList.setType("Type Test");
        bidList.setBidQuantity(BigDecimal.valueOf(10.0));
        bidList.setAsk(100.10);
        bidList.setCreationDate(new Timestamp(System.currentTimeMillis()));

        bidListDTO = new BidListDTO();
        bidListDTO.setBidListId(1);
        bidListDTO.setAccount("Test Account");
        bidListDTO.setType("Type Test");
    }

    @Test
    public void testGetAllBidsList() {
        when(bidListRepository.findAll()).thenReturn(List.of(bidList));

        List<BidList> result = bidListService.getAllBidsList();

        assertEquals(1, result.size());
        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllBidListDTO() {
        when(bidListRepository.findAll()).thenReturn(List.of(bidList));
        when(bidListMapper.tobidListDTO(bidList)).thenReturn(bidListDTO);

        List<BidListDTO> result = bidListService.getAllBidListDTO();

        assertEquals(1, result.size());
        verify(bidListRepository, times(1)).findAll();
        verify(bidListMapper, times(1)).tobidListDTO(bidList);
    }

    @Test
    public void testGetBidListIdFound() {
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bidList));

        BidList result = bidListService.getBidListId(1);

        assertNotNull(result);
        assertEquals(1, result.getBidListId());
        verify(bidListRepository, times(1)).findById(1);
    }

    @Test
    public void testGetBidListIdNotFound() {
        when(bidListRepository.findById(999)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            bidListService.getBidListId(999); // ID inexistant
        });

        assertEquals("Bid with ID 999 not found", exception.getMessage());
        verify(bidListRepository, times(1)).findById(999);
    }

    @Test
    public void testSaveBidList() {
        when(bidListRepository.save(bidList)).thenReturn(bidList);

        BidList result = bidListService.save(bidList);

        assertNotNull(result);
        verify(bidListRepository, times(1)).save(bidList);
    }

    @Test
    public void testDeleteBidList() {
        bidListService.delete(bidList);

        verify(bidListRepository, times(1)).delete(bidList);
    }

    @Test
    public void testUpdateBidList() {
        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bidList));
        when(bidListMapper.tobidListDTO(bidList)).thenReturn(bidListDTO);
        when(bidListRepository.save(any(BidList.class))).thenReturn(bidList);

        bidListService.update(bidListDTO, bidList);

        verify(bidListRepository, times(1)).findById(anyInt());
        verify(bidListRepository, times(1)).save(any(BidList.class));
    }

    @Test
    public void testAddBidList() {
        when(bidListMapper.tobidList(bidListDTO)).thenReturn(bidList);
        when(bidListRepository.save(any(BidList.class))).thenReturn(bidList);

        bidListService.add(bidListDTO);

        verify(bidListMapper, times(1)).tobidList(bidListDTO);
        verify(bidListRepository, times(1)).save(any(BidList.class));
    }
}
