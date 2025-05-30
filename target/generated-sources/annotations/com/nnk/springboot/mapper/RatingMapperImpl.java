package com.nnk.springboot.mapper;

import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.model.Rating;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T20:38:22+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250526-2018, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class RatingMapperImpl implements RatingMapper {

    @Override
    public RatingDTO toRatingDTO(Rating rating) {
        if ( rating == null ) {
            return null;
        }

        RatingDTO ratingDTO = new RatingDTO();

        ratingDTO.setFitchRating( rating.getFitchRating() );
        ratingDTO.setId( rating.getId() );
        ratingDTO.setMoodysRating( rating.getMoodysRating() );
        ratingDTO.setOrderNumber( rating.getOrderNumber() );
        ratingDTO.setSandPRating( rating.getSandPRating() );

        return ratingDTO;
    }

    @Override
    public Rating toRating(RatingDTO ratingDTO) {
        if ( ratingDTO == null ) {
            return null;
        }

        Rating rating = new Rating();

        rating.setFitchRating( ratingDTO.getFitchRating() );
        if ( ratingDTO.getId() != null ) {
            rating.setId( ratingDTO.getId() );
        }
        rating.setMoodysRating( ratingDTO.getMoodysRating() );
        rating.setOrderNumber( ratingDTO.getOrderNumber() );
        rating.setSandPRating( ratingDTO.getSandPRating() );

        return rating;
    }

    @Override
    public void updateRatingListFromDTO(RatingDTO dto, Rating entity) {
        if ( dto == null ) {
            return;
        }

        entity.setFitchRating( dto.getFitchRating() );
        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        entity.setMoodysRating( dto.getMoodysRating() );
        entity.setOrderNumber( dto.getOrderNumber() );
        entity.setSandPRating( dto.getSandPRating() );
    }
}
