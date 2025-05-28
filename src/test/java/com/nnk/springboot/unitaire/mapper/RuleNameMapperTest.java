package com.nnk.springboot.unitaire.mapper;

import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.mapper.RuleNameMapper;
import com.nnk.springboot.model.RuleName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class RuleNameMapperTest {

    private RuleNameMapper ruleNameMapper;

    @BeforeEach
    public void setUp() {
        ruleNameMapper = Mappers.getMapper(RuleNameMapper.class);
    }

    @Test
    public void testToRuleNameDTO() {
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Test Rule");
        ruleName.setDescription("Test Description");
        ruleName.setJson("Test JSON");
        ruleName.setTemplate("Test Template");
        ruleName.setSqlStr("Test SQL");
        ruleName.setSqlPart("Test SQL Part");

        RuleNameDTO ruleNameDTO = ruleNameMapper.toRuleNameDTO(ruleName);

        assertNotNull(ruleNameDTO);
        assertEquals(ruleName.getId(), ruleNameDTO.getId());
        assertEquals(ruleName.getName(), ruleNameDTO.getName());
        assertEquals(ruleName.getDescription(), ruleNameDTO.getDescription());
        assertEquals(ruleName.getJson(), ruleNameDTO.getJson());
        assertEquals(ruleName.getTemplate(), ruleNameDTO.getTemplate());
        assertEquals(ruleName.getSqlStr(), ruleNameDTO.getSqlStr());
        assertEquals(ruleName.getSqlPart(), ruleNameDTO.getSqlPart());
    }

    @Test
    public void testToRuleName() {
        RuleNameDTO ruleNameDTO = new RuleNameDTO();
        ruleNameDTO.setId(1);
        ruleNameDTO.setName("Test Rule");
        ruleNameDTO.setDescription("Test Description");
        ruleNameDTO.setJson("Test JSON");
        ruleNameDTO.setTemplate("Test Template");
        ruleNameDTO.setSqlStr("Test SQL");
        ruleNameDTO.setSqlPart("Test SQL Part");

        RuleName ruleName = ruleNameMapper.toRuleName(ruleNameDTO);

        assertNotNull(ruleName);
        assertEquals(ruleNameDTO.getId(), ruleName.getId());
        assertEquals(ruleNameDTO.getName(), ruleName.getName());
        assertEquals(ruleNameDTO.getDescription(), ruleName.getDescription());
        assertEquals(ruleNameDTO.getJson(), ruleName.getJson());
        assertEquals(ruleNameDTO.getTemplate(), ruleName.getTemplate());
        assertEquals(ruleNameDTO.getSqlStr(), ruleName.getSqlStr());
        assertEquals(ruleNameDTO.getSqlPart(), ruleName.getSqlPart());
    }

    @Test
    public void testUpdateRuleNameListFormDTO() {
        RuleNameDTO ruleNameDTO = new RuleNameDTO();
        ruleNameDTO.setName("Updated Rule");
        ruleNameDTO.setDescription("Updated Description");
        ruleNameDTO.setJson("Updated JSON");
        ruleNameDTO.setTemplate("Updated Template");

        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Old Rule");
        ruleName.setDescription("Old Description");
        ruleName.setJson("Old JSON");
        ruleName.setTemplate("Old Template");

        ruleNameMapper.updateRuleNameListFormDTO(ruleNameDTO, ruleName);

        assertEquals("Updated Rule", ruleName.getName());
        assertEquals("Updated Description", ruleName.getDescription());
        assertEquals("Updated JSON", ruleName.getJson());
        assertEquals("Updated Template", ruleName.getTemplate());
    }
}
