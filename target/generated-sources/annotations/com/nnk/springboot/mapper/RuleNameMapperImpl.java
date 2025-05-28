package com.nnk.springboot.mapper;

import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.model.RuleName;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-29T02:14:29+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.v20250526-2018, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class RuleNameMapperImpl implements RuleNameMapper {

    @Override
    public RuleNameDTO toRuleNameDTO(RuleName ruleName) {
        if ( ruleName == null ) {
            return null;
        }

        RuleNameDTO ruleNameDTO = new RuleNameDTO();

        ruleNameDTO.setDescription( ruleName.getDescription() );
        ruleNameDTO.setId( ruleName.getId() );
        ruleNameDTO.setJson( ruleName.getJson() );
        ruleNameDTO.setName( ruleName.getName() );
        ruleNameDTO.setSqlPart( ruleName.getSqlPart() );
        ruleNameDTO.setSqlStr( ruleName.getSqlStr() );
        ruleNameDTO.setTemplate( ruleName.getTemplate() );

        return ruleNameDTO;
    }

    @Override
    public RuleName toRuleName(RuleNameDTO ruleNameDTO) {
        if ( ruleNameDTO == null ) {
            return null;
        }

        RuleName ruleName = new RuleName();

        ruleName.setDescription( ruleNameDTO.getDescription() );
        if ( ruleNameDTO.getId() != null ) {
            ruleName.setId( ruleNameDTO.getId() );
        }
        ruleName.setJson( ruleNameDTO.getJson() );
        ruleName.setName( ruleNameDTO.getName() );
        ruleName.setSqlPart( ruleNameDTO.getSqlPart() );
        ruleName.setSqlStr( ruleNameDTO.getSqlStr() );
        ruleName.setTemplate( ruleNameDTO.getTemplate() );

        return ruleName;
    }

    @Override
    public void updateRuleNameListFormDTO(RuleNameDTO dto, RuleName entity) {
        if ( dto == null ) {
            return;
        }

        entity.setDescription( dto.getDescription() );
        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        entity.setJson( dto.getJson() );
        entity.setName( dto.getName() );
        entity.setSqlPart( dto.getSqlPart() );
        entity.setSqlStr( dto.getSqlStr() );
        entity.setTemplate( dto.getTemplate() );
    }
}
