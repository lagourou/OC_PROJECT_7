package com.nnk.springboot.unitaire.mapper;

import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.mapper.RatingMapper;
import com.nnk.springboot.model.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class RatingMapperTest {

    private RatingMapper ratingMapper;

    @BeforeEach
    public void setUp() {
        ratingMapper = Mappers.getMapper(RatingMapper.class);
    }

    @Test
    public void testToRatingDTO() {
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("AAA");
        rating.setSandPRating("AA+");
        rating.setFitchRating("A+");
        rating.setOrderNumber(1);

        RatingDTO ratingDTO = ratingMapper.toRatingDTO(rating);

        assertNotNull(ratingDTO);
        assertEquals(rating.getId(), ratingDTO.getId());
        assertEquals(rating.getMoodysRating(), ratingDTO.getMoodysRating());
        assertEquals(rating.getSandPRating(), ratingDTO.getSandPRating());
        assertEquals(rating.getFitchRating(), ratingDTO.getFitchRating());
        assertEquals(rating.getOrderNumber(), ratingDTO.getOrderNumber());
    }

    @Test
    public void testToRating() {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(1);
        ratingDTO.setMoodysRating("AAA");
        ratingDTO.setSandPRating("AA+");
        ratingDTO.setFitchRating("A+");
        ratingDTO.setOrderNumber(1);

        Rating rating = ratingMapper.toRating(ratingDTO);

        assertNotNull(rating);
        assertEquals(ratingDTO.getId(), rating.getId());
        assertEquals(ratingDTO.getMoodysRating(), rating.getMoodysRating());
        assertEquals(ratingDTO.getSandPRating(), rating.getSandPRating());
        assertEquals(ratingDTO.getFitchRating(), rating.getFitchRating());
        assertEquals(ratingDTO.getOrderNumber(), rating.getOrderNumber());
    }

    @Test
    public void testUpdateRatingListFromDTO() {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setMoodysRating("Updated AAA");
        ratingDTO.setSandPRating("Updated AA+");
        ratingDTO.setFitchRating("Updated A+");
        ratingDTO.setOrderNumber(2);

        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("AAA");
        rating.setSandPRating("AA+");
        rating.setFitchRating("A+");
        rating.setOrderNumber(1);

        ratingMapper.updateRatingListFromDTO(ratingDTO, rating);

        assertEquals("Updated AAA", rating.getMoodysRating());
        assertEquals("Updated AA+", rating.getSandPRating());
        assertEquals("Updated A+", rating.getFitchRating());
        assertEquals(2, rating.getOrderNumber());
    }
}
