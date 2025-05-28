package com.nnk.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.model.Rating;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RatingMapper {

    RatingDTO toRatingDTO(Rating rating);

    Rating toRating(RatingDTO ratingDTO);

    void updateRatingListFromDTO(RatingDTO dto, @MappingTarget Rating entity);

}
