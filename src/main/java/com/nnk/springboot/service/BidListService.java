package com.nnk.springboot.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.mapper.BidListMapper;
import com.nnk.springboot.model.BidList;
import com.nnk.springboot.repositories.BidListRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * Service class for managing BidList operations.
 */
@Service
@RequiredArgsConstructor
public class BidListService {

    private final BidListRepository bidListRepository;
    private final BidListMapper bidListMappper;

    /**
     * Get all BidList entries.
     *
     * @return list of BidList
     */
    public List<BidList> getAllBidsList() {
        return bidListRepository.findAll();
    }

    /**
     * Get all BidList entries as DTOs.
     *
     * @return list of BidListDTO
     */
    public List<BidListDTO> getAllBidListDTO() {
        return bidListRepository.findAll()
                .stream()
                .map(bidListMappper::tobidListDTO)
                .toList();
    }

    /**
     * Get a BidList by its ID.
     *
     * @param bidListId the bid ID
     * @return the found BidList
     * @throws EntityNotFoundException if not found
     */
    public BidList getBidListId(int bidListId) {
        return bidListRepository.findById(bidListId)
                .orElseThrow(() -> new EntityNotFoundException("Bid with ID " + bidListId + " not found"));
    }

    /**
     * Save a BidList to the database.
     *
     * @param bidList the bid to save
     * @return the saved BidList
     */
    public BidList save(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    /**
     * Delete a BidList from the database.
     *
     * @param bidList the bid to delete
     */
    public void delete(BidList bidList) {
        bidListRepository.delete(bidList);
    }

    /**
     * Update an existing BidList using a DTO and a source object.
     *
     * @param updateBid     the DTO with the bid ID
     * @param updateBidList the object with new values
     */
    public void update(BidListDTO updateBid, BidList updateBidList) {
        BidList bidList = getBidListId(updateBid.getBidListId());
        bidListMappper.updateBidListFromDTO(updateBid, bidList);

        bidList.setAskQuantity(updateBidList.getAskQuantity());
        bidList.setBid(updateBidList.getBid());
        bidList.setAsk(updateBidList.getAsk());
        bidList.setBenchmark(updateBidList.getBenchmark());
        bidList.setBidListDate(new Timestamp(System.currentTimeMillis()));
        bidList.setCommentary(updateBidList.getCommentary());
        bidList.setSecurity(updateBidList.getSecurity());
        bidList.setStatus(updateBidList.getStatus());
        bidList.setTrader(updateBidList.getTrader());
        bidList.setBook(updateBidList.getBook());
        bidList.setCreationName(updateBidList.getCreationName());
        bidList.setRevisionName(updateBidList.getRevisionName());
        bidList.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        bidList.setDealName(updateBidList.getDealName());
        bidList.setDealType(updateBidList.getDealType());
        bidList.setSourceListId(updateBidList.getSourceListId());
        bidList.setSide(updateBidList.getSide());

        save(bidList);
    }

    /**
     * Convert a list of BidList to DTOs.
     *
     * @param bidLists list of BidList
     * @return list of BidListDTO
     */
    public List<BidListDTO> getBidListDTO(List<BidList> bidLists) {
        return bidLists.stream()
                .map(bidListMappper::tobidListDTO)
                .toList();
    }

    /**
     * Add a new BidList from a DTO.
     *
     * @param bidListDTO the DTO to convert and save
     */
    public void add(BidListDTO bidListDTO) {
        BidList bidList = bidListMappper.tobidList(bidListDTO);
        bidList.setCreationDate(new Timestamp(System.currentTimeMillis()));
        save(bidList);
    }

}
