package com.nnk.springboot.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.mapper.RuleNameMapper;
import com.nnk.springboot.model.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * Service class for managing RuleName operations.
 */
@Service
@RequiredArgsConstructor
public class RuleNameService {

    private final RuleNameRepository ruleNameRepository;
    private final RuleNameMapper ruleNameMapper;

    /**
     * Get all RuleName entries.
     *
     * @return list of RuleName
     */
    public List<RuleName> getAllRuleName() {
        return ruleNameRepository.findAll();
    }

    /**
     * Get all RuleName entries as DTOs.
     *
     * @return list of RuleNameDTO
     */
    public List<RuleNameDTO> getAllRuleNameListDTO() {
        return ruleNameRepository.findAll()
                .stream()
                .map(ruleNameMapper::toRuleNameDTO)
                .toList();
    }

    /**
     * Get a RuleName by its ID.
     *
     * @param id the rule ID
     * @return the found RuleName
     * @throws EntityNotFoundException if not found
     */
    public RuleName getRuleId(int id) {
        return ruleNameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rule with ID " + id + " not found"));
    }

    /**
     * Save a RuleName to the database.
     *
     * @param ruleName the rule to save
     * @return the saved RuleName
     */
    public RuleName save(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Delete a RuleName from the database.
     *
     * @param ruleName the rule to delete
     */
    public void delete(RuleName ruleName) {
        ruleNameRepository.delete(ruleName);
    }

    /**
     * Update a RuleName using a DTO.
     *
     * @param updateRule the DTO with updated values
     */
    public void update(RuleNameDTO updateRule) {
        RuleName ruleName = getRuleId(updateRule.getId());
        ruleNameMapper.updateRuleNameListFormDTO(updateRule, ruleName);
        save(ruleName);
    }

    /**
     * Convert a list of RuleName to DTOs.
     *
     * @param ruleNames list of RuleName
     * @return list of RuleNameDTO
     */
    public List<RuleNameDTO> getListRuleNameDTO(List<RuleName> ruleNames) {
        return ruleNames.stream()
                .map(ruleNameMapper::toRuleNameDTO)
                .toList();
    }

    /**
     * Add a new RuleName from a DTO.
     *
     * @param ruleNameDTO the DTO to convert and save
     */
    public void add(RuleNameDTO ruleNameDTO) {
        RuleName ruleName = ruleNameMapper.toRuleName(ruleNameDTO);
        ruleName.setCreationDate(new Timestamp(System.currentTimeMillis()));
        save(ruleName);
    }

}
