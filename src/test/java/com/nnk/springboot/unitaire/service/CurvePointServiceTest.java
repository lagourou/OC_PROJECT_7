package com.nnk.springboot.unitaire.service;

import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.mapper.CurvePointMapper;
import com.nnk.springboot.model.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CurvePointServiceTest {

    @Mock
    private CurvePointRepository curvePointRepository;

    @Mock
    private CurvePointMapper curvePointMapper;

    @InjectMocks
    private CurvePointService curvePointService;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCurvesList() {
        CurvePoint curve = new CurvePoint();
        when(curvePointRepository.findAll()).thenReturn(List.of(curve));

        List<CurvePoint> result = curvePointService.getAllCurvesList();

        assertEquals(1, result.size());
        verify(curvePointRepository).findAll();
    }

    @Test
    void testGetAllCurvePointListDTO() {
        CurvePoint curve = new CurvePoint();
        CurvePointDTO dto = new CurvePointDTO();

        when(curvePointRepository.findAll()).thenReturn(List.of(curve));
        when(curvePointMapper.toCurvePointDTO(curve)).thenReturn(dto);

        List<CurvePointDTO> result = curvePointService.getAllCurvePointListDTO();

        assertEquals(1, result.size());
        verify(curvePointMapper).toCurvePointDTO(curve);
    }

    @Test
    void testGetCurvePointIdFound() {
        CurvePoint curve = new CurvePoint();
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curve));

        CurvePoint result = curvePointService.getCurvePointId(1);

        assertNotNull(result);
        verify(curvePointRepository).findById(1);
    }

    @Test
    void testGetCurvePointIdNotFound() {
        when(curvePointRepository.findById(1)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> curvePointService.getCurvePointId(1));
        assertEquals("Curve with ID 1 not found", ex.getMessage());

    }

    @Test
    void testSave() {
        CurvePoint curve = new CurvePoint();
        when(curvePointRepository.save(curve)).thenReturn(curve);

        CurvePoint result = curvePointService.save(curve);

        assertEquals(curve, result);
        verify(curvePointRepository).save(curve);
    }

    @Test
    void testDelete() {
        CurvePoint curve = new CurvePoint();

        curvePointService.delete(curve);

        verify(curvePointRepository).delete(curve);
    }

    @Test
    void testUpdate() {
        CurvePoint existingCurve = new CurvePoint();
        CurvePointDTO dto = new CurvePointDTO();
        dto.setId(1);

        when(curvePointRepository.findById(1)).thenReturn(Optional.of(existingCurve));

        curvePointService.update(dto, existingCurve);

        verify(curvePointMapper).updateCurvePointListFromDTO(dto, existingCurve);
        verify(curvePointRepository).save(existingCurve);
    }

    @Test
    void testGetListCurvePointDTO() {
        CurvePoint c1 = new CurvePoint();
        CurvePointDTO dto1 = new CurvePointDTO();

        when(curvePointMapper.toCurvePointDTO(c1)).thenReturn(dto1);

        List<CurvePointDTO> result = curvePointService.getListCurvePointDTO(List.of(c1));

        assertEquals(1, result.size());
        verify(curvePointMapper).toCurvePointDTO(c1);
    }

    @Test
    void testAdd() {
        CurvePointDTO dto = new CurvePointDTO();
        CurvePoint entity = new CurvePoint();

        when(curvePointMapper.toCurvePoint(dto)).thenReturn(entity);
        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(entity);

        curvePointService.add(dto);

        verify(curvePointMapper).toCurvePoint(dto);
        verify(curvePointRepository).save(entity);
        assertNotNull(entity.getCreationDate());
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
}
