package com.nnk.springboot.mapper;

import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.model.CurvePoint;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T20:38:21+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250526-2018, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class CurvePointMapperImpl implements CurvePointMapper {

    @Override
    public CurvePointDTO toCurvePointDTO(CurvePoint curvepoint) {
        if ( curvepoint == null ) {
            return null;
        }

        CurvePointDTO curvePointDTO = new CurvePointDTO();

        curvePointDTO.setCurveId( curvepoint.getCurveId() );
        curvePointDTO.setId( curvepoint.getId() );
        curvePointDTO.setTerm( curvepoint.getTerm() );
        curvePointDTO.setValue( curvepoint.getValue() );

        return curvePointDTO;
    }

    @Override
    public CurvePoint toCurvePoint(CurvePointDTO curvePointDTO) {
        if ( curvePointDTO == null ) {
            return null;
        }

        CurvePoint curvePoint = new CurvePoint();

        curvePoint.setCurveId( curvePointDTO.getCurveId() );
        if ( curvePointDTO.getId() != null ) {
            curvePoint.setId( curvePointDTO.getId() );
        }
        curvePoint.setTerm( curvePointDTO.getTerm() );
        curvePoint.setValue( curvePointDTO.getValue() );

        return curvePoint;
    }

    @Override
    public void updateCurvePointListFromDTO(CurvePointDTO dto, CurvePoint entity) {
        if ( dto == null ) {
            return;
        }

        entity.setCurveId( dto.getCurveId() );
        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        entity.setTerm( dto.getTerm() );
        entity.setValue( dto.getValue() );
    }
}
