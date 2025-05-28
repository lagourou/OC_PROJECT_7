package com.nnk.springboot.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.mapper.CurvePointMapper;
import com.nnk.springboot.model.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * Service class for managing CurvePoint operations.
 */
@Service
@RequiredArgsConstructor
public class CurvePointService {

    private final CurvePointRepository curvePointRepository;
    private final CurvePointMapper curvePointMappper;

    /**
     * Get all CurvePoint entries.
     *
     * @return list of CurvePoint
     */
    public List<CurvePoint> getAllCurvesList() {
        return curvePointRepository.findAll();
    }

    /**
     * Get all CurvePoint entries as DTOs.
     *
     * @return list of CurvePointDTO
     */
    public List<CurvePointDTO> getAllCurvePointListDTO() {
        return curvePointRepository.findAll()
                .stream()
                .map(curvePointMappper::toCurvePointDTO)
                .toList();
    }

    /**
     * Get a CurvePoint by its ID.
     *
     * @param id the curve ID
     * @return the found CurvePoint
     * @throws EntityNotFoundException if not found
     */
    public CurvePoint getCurvePointId(int id) {
        return curvePointRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curve with ID " + id + " not found"));
    }

    /**
     * Save a CurvePoint to the database.
     *
     * @param curvePoint the curve to save
     * @return the saved CurvePoint
     */
    public CurvePoint save(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Delete a CurvePoint from the database.
     *
     * @param curvepoint the curve to delete
     */
    public void delete(CurvePoint curvepoint) {
        curvePointRepository.delete(curvepoint);
    }

    /**
     * Update a CurvePoint using a DTO and a source object.
     *
     * @param updateCurve      the DTO with the curve ID
     * @param updateCurvePoint the object with new values
     */
    public void update(CurvePointDTO updateCurve, CurvePoint updateCurvePoint) {

        CurvePoint curvePoint = getCurvePointId(updateCurve.getId());
        curvePointMappper.updateCurvePointListFromDTO(updateCurve, curvePoint);

        curvePoint.setAsOfDate(new Timestamp(System.currentTimeMillis()));
        save(curvePoint);
    }

    /**
     * Convert a list of CurvePoint to DTOs.
     *
     * @param curvePoints list of CurvePoint
     * @return list of CurvePointDTO
     */
    public List<CurvePointDTO> getListCurvePointDTO(List<CurvePoint> curvePoints) {
        return curvePoints.stream()
                .map(curvePointMappper::toCurvePointDTO)
                .toList();
    }

    /**
     * Add a new CurvePoint from a DTO.
     *
     * @param curvePointDTO the DTO to convert and save
     */
    public void add(CurvePointDTO curvePointDTO) {
        CurvePoint curvePoint = curvePointMappper.toCurvePoint(curvePointDTO);
        curvePoint.setCreationDate(new Timestamp(System.currentTimeMillis()));
        save(curvePoint);
    }

}
