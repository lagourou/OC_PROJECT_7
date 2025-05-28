package com.nnk.springboot.unitaire.service;

import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.mapper.TradeMapper;
import com.nnk.springboot.model.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.*;
import org.mockito.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private TradeMapper tradeMapper;

    @InjectMocks
    private TradeService tradeService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testGetAllTrade() {
        Trade trade = new Trade();
        when(tradeRepository.findAll()).thenReturn(List.of(trade));

        List<Trade> result = tradeService.getAllTrade();

        assertEquals(1, result.size());
        verify(tradeRepository).findAll();
    }

    @Test
    void testGetAllTradeListDTO() {
        Trade trade = new Trade();
        TradeDTO dto = new TradeDTO();

        when(tradeRepository.findAll()).thenReturn(List.of(trade));
        when(tradeMapper.toTradeDTO(trade)).thenReturn(dto);

        List<TradeDTO> result = tradeService.getAllTradeListDTO();

        assertEquals(1, result.size());
        verify(tradeMapper).toTradeDTO(trade);
    }

    @Test
    void testGetTradeIdFound() {
        Trade trade = new Trade();
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        Trade result = tradeService.getTradeId(1);

        assertNotNull(result);
        verify(tradeRepository).findById(1);
    }

    @Test
    void testGetTradeIdNotFound() {
        when(tradeRepository.findById(1)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> tradeService.getTradeId(1));
        assertEquals("Trade with ID 1 not found", ex.getMessage());
    }

    @Test
    void testSaveTrade() {
        Trade trade = new Trade();
        when(tradeRepository.save(trade)).thenReturn(trade);

        Trade result = tradeService.save(trade);

        assertEquals(trade, result);
        verify(tradeRepository).save(trade);
    }

    @Test
    void testDeleteTrade() {
        Trade trade = new Trade();
        tradeService.delete(trade);

        verify(tradeRepository).delete(trade);
    }

    @Test
    void testUpdateTrade() {
        Trade trade = new Trade();
        TradeDTO dto = new TradeDTO();
        Trade updated = new Trade();

        dto.setTradeId(1);
        updated.setSellQuantity(10d);
        updated.setBuyPrice(100d);
        updated.setTradeDate(new Timestamp(System.currentTimeMillis()));

        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        tradeService.update(dto, updated);

        verify(tradeMapper).updateTradeListFromDTO(dto, trade);
        verify(tradeRepository).save(trade);

        assertEquals(10d, trade.getSellQuantity());
        assertEquals(100d, trade.getBuyPrice());
        assertNotNull(trade.getTradeDate());
    }

    @Test
    void testGetListTradeDTO() {
        Trade trade = new Trade();
        TradeDTO dto = new TradeDTO();

        when(tradeMapper.toTradeDTO(trade)).thenReturn(dto);

        List<TradeDTO> result = tradeService.getListTradeDTO(List.of(trade));

        assertEquals(1, result.size());
        verify(tradeMapper).toTradeDTO(trade);
    }

    @Test
    void testAddTrade() {
        TradeDTO dto = new TradeDTO();
        Trade trade = new Trade();

        when(tradeMapper.toTrade(dto)).thenReturn(trade);
        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);

        tradeService.add(dto);

        verify(tradeMapper).toTrade(dto);
        verify(tradeRepository).save(trade);
        assertNotNull(trade.getCreationDate());
    }
}
