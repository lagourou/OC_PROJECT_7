package com.nnk.springboot.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.mapper.RatingMapper;
import com.nnk.springboot.model.Rating;
import com.nnk.springboot.repositories.RatingRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * Service class for managing Rating operations.
 */
@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    /**
     * Get all Rating entries.
     *
     * @return list of Rating
     */
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    /**
     * Get all Rating entries as DTOs.
     *
     * @return list of RatingDTO
     */
    public List<RatingDTO> getAllRatingListDTO() {
        return ratingRepository.findAll()
                .stream()
                .map(ratingMapper::toRatingDTO)
                .toList();
    }

    /**
     * Get a Rating by its ID.
     *
     * @param id the rating ID
     * @return the found Rating
     * @throws EntityNotFoundException if not found
     */
    public Rating getRatingId(int id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rating with ID " + id + " not found"));
    }

    /**
     * Save a Rating to the database.
     *
     * @param rating the rating to save
     * @return the saved Rating
     */
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Delete a Rating from the database.
     *
     * @param rating the rating to delete
     */
    public void delete(Rating rating) {
        ratingRepository.delete(rating);
    }

    /**
     * Update an existing Rating using a DTO.
     *
     * @param updateRating the DTO with updated values
     */
    public void update(RatingDTO updateRating) {
        Rating rating = getRatingId(updateRating.getId());
        ratingMapper.updateRatingListFromDTO(updateRating, rating);
        save(rating);
    }

    /**
     * Convert a list of Rating to DTOs.
     *
     * @param ratings list of Rating
     * @return list of RatingDTO
     */
    public List<RatingDTO> getListRatingDTO(List<Rating> ratings) {
        return ratings.stream()
                .map(ratingMapper::toRatingDTO)
                .toList();
    }

    /**
     * Add a new Rating from a DTO.
     *
     * @param ratingDTO the DTO to convert and save
     */
    public void add(RatingDTO ratingDTO) {
        Rating rating = ratingMapper.toRating(ratingDTO);

        rating.setCreationDate(new Timestamp(System.currentTimeMillis()));
        save(rating);
    }

}
