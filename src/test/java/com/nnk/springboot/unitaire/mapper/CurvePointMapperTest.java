package com.nnk.springboot.unitaire.mapper;

import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.mapper.CurvePointMapper;
import com.nnk.springboot.model.CurvePoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class CurvePointMapperTest {

    private CurvePointMapper curvePointMapper;

    @BeforeEach
    public void setUp() {
        curvePointMapper = Mappers.getMapper(CurvePointMapper.class);
    }

    @Test
    public void testToCurvePointDTO() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(10);
        curvePoint.setTerm(5.0);
        curvePoint.setValue(100.0);

        CurvePointDTO curvePointDTO = curvePointMapper.toCurvePointDTO(curvePoint);

        assertNotNull(curvePointDTO);
        assertEquals(curvePoint.getId(), curvePointDTO.getId());
        assertEquals(curvePoint.getCurveId(), curvePointDTO.getCurveId());
        assertEquals(curvePoint.getTerm(), curvePointDTO.getTerm());
        assertEquals(curvePoint.getValue(), curvePointDTO.getValue());
    }

    @Test
    public void testToCurvePoint() {
        CurvePointDTO curvePointDTO = new CurvePointDTO();
        curvePointDTO.setId(1);
        curvePointDTO.setCurveId(10);
        curvePointDTO.setTerm(5.0);
        curvePointDTO.setValue(100.0);

        CurvePoint curvePoint = curvePointMapper.toCurvePoint(curvePointDTO);

        assertNotNull(curvePoint);
        assertEquals(curvePointDTO.getId(), curvePoint.getId());
        assertEquals(curvePointDTO.getCurveId(), curvePoint.getCurveId());
        assertEquals(curvePointDTO.getTerm(), curvePoint.getTerm());
        assertEquals(curvePointDTO.getValue(), curvePoint.getValue());
    }

    @Test
    public void testUpdateCurvePointFromDTO() {
        CurvePointDTO curvePointDTO = new CurvePointDTO();
        curvePointDTO.setCurveId(20);
        curvePointDTO.setTerm(10.0);
        curvePointDTO.setValue(200.0);

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(10);
        curvePoint.setTerm(5.0);
        curvePoint.setValue(100.0);

        curvePointMapper.updateCurvePointListFromDTO(curvePointDTO, curvePoint);

        assertEquals(20, curvePoint.getCurveId());
        assertEquals(10.0, curvePoint.getTerm());
        assertEquals(200.0, curvePoint.getValue());
    }
}
