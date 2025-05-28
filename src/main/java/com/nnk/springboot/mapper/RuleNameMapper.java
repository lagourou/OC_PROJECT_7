package com.nnk.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.model.RuleName;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RuleNameMapper {

    RuleNameDTO toRuleNameDTO(RuleName ruleName);

    RuleName toRuleName(RuleNameDTO ruleNameDTO);

    void updateRuleNameListFormDTO(RuleNameDTO dto, @MappingTarget RuleName entity);

}
