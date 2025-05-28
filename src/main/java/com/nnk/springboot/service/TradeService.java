package com.nnk.springboot.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.mapper.TradeMapper;
import com.nnk.springboot.model.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * Service class for managing Trade operations.
 */
@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;
    private final TradeMapper tradeMapper;

    /**
     * Get all Trade entries.
     *
     * @return list of Trade
     */
    public List<Trade> getAllTrade() {
        return tradeRepository.findAll();
    }

    /**
     * Get all Trade entries as DTOs.
     *
     * @return list of TradeDTO
     */
    public List<TradeDTO> getAllTradeListDTO() {
        return tradeRepository.findAll()
                .stream()
                .map(tradeMapper::toTradeDTO)
                .toList();
    }

    /**
     * Get a Trade by its ID.
     *
     * @param tradeId the trade ID
     * @return the found Trade
     * @throws EntityNotFoundException if not found
     */
    public Trade getTradeId(int tradeId) {
        return tradeRepository.findById(tradeId)
                .orElseThrow(() -> new EntityNotFoundException("Trade with ID " + tradeId + " not found"));
    }

    /**
     * Save a Trade to the database.
     *
     * @param trade the trade to save
     * @return the saved Trade
     */
    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    /**
     * Delete a Trade from the database.
     *
     * @param trade the trade to delete
     */
    public void delete(Trade trade) {
        tradeRepository.delete(trade);
    }

    /**
     * Update an existing Trade using DTO and additional data.
     *
     * @param updateTrade     the DTO with updated values
     * @param updateTradeList the Trade with extra values to update
     */
    public void update(TradeDTO updateTrade, Trade updateTradeList) {

        Trade trade = getTradeId(updateTrade.getTradeId());
        tradeMapper.updateTradeListFromDTO(updateTrade, trade);

        trade.setSellQuantity(updateTradeList.getSellQuantity());
        trade.setBuyPrice(updateTradeList.getBuyPrice());
        trade.setTradeDate(new Timestamp(System.currentTimeMillis()));
        trade.setSecurity(updateTradeList.getSecurity());
        trade.setStatus(updateTradeList.getStatus());
        trade.setTrader(updateTradeList.getTrader());
        trade.setBenchmark(updateTradeList.getBenchmark());
        trade.setBook(updateTradeList.getBook());
        trade.setCreationName(updateTradeList.getCreationName());
        trade.setRevisionName(updateTradeList.getRevisionName());
        trade.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        trade.setDealName(updateTradeList.getDealName());
        trade.setDealType(updateTradeList.getDealType());
        trade.setSourceListId(updateTradeList.getSourceListId());
        trade.setSide(updateTradeList.getSide());

        save(trade);
    }

    /**
     * Convert a list of Trade to DTOs.
     *
     * @param trades list of Trade
     * @return list of TradeDTO
     */
    public List<TradeDTO> getListTradeDTO(List<Trade> trades) {
        return trades.stream()
                .map(tradeMapper::toTradeDTO)
                .toList();
    }

    /**
     * Add a new Trade using a DTO.
     *
     * @param tradeDTO the DTO to convert and save
     */
    public void add(TradeDTO tradeDTO) {
        Trade trade = tradeMapper.toTrade(tradeDTO);

        trade.setCreationDate(new Timestamp(System.currentTimeMillis()));
        save(trade);
    }

}
