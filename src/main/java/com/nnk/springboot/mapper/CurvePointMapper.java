package com.nnk.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.model.CurvePoint;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurvePointMapper {

    CurvePointDTO toCurvePointDTO(CurvePoint curvepoint);

    CurvePoint toCurvePoint(CurvePointDTO curvePointDTO);

    void updateCurvePointListFromDTO(CurvePointDTO dto, @MappingTarget CurvePoint entity);

}
