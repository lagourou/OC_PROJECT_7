package com.nnk.springboot.unitaire.service;

import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.mapper.RuleNameMapper;
import com.nnk.springboot.model.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RuleNameServiceTest {

    @Mock
    private RuleNameRepository ruleNameRepository;

    @Mock
    private RuleNameMapper ruleNameMapper;

    @InjectMocks
    private RuleNameService ruleNameService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRuleName() {
        RuleName rule = new RuleName();
        when(ruleNameRepository.findAll()).thenReturn(List.of(rule));

        List<RuleName> result = ruleNameService.getAllRuleName();

        assertEquals(1, result.size());
        verify(ruleNameRepository).findAll();
    }

    @Test
    void testGetAllRuleNameListDTO() {
        RuleName rule = new RuleName();
        RuleNameDTO dto = new RuleNameDTO();

        when(ruleNameRepository.findAll()).thenReturn(List.of(rule));
        when(ruleNameMapper.toRuleNameDTO(rule)).thenReturn(dto);

        List<RuleNameDTO> result = ruleNameService.getAllRuleNameListDTO();

        assertEquals(1, result.size());
        verify(ruleNameMapper).toRuleNameDTO(rule);
    }

    @Test
    void testGetRuleIdFound() {
        RuleName rule = new RuleName();
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(rule));

        RuleName result = ruleNameService.getRuleId(1);

        assertNotNull(result);
        verify(ruleNameRepository).findById(1);
    }

    @Test
    void testGetRuleIdNotFound() {
        when(ruleNameRepository.findById(1)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> ruleNameService.getRuleId(1));
        assertEquals("Rule with ID 1 not found", ex.getMessage());
    }

    @Test
    void testSave() {
        RuleName rule = new RuleName();
        when(ruleNameRepository.save(rule)).thenReturn(rule);

        RuleName result = ruleNameService.save(rule);

        assertEquals(rule, result);
        verify(ruleNameRepository).save(rule);
    }

    @Test
    void testDelete() {
        RuleName rule = new RuleName();

        ruleNameService.delete(rule);

        verify(ruleNameRepository).delete(rule);
    }

    @Test
    void testUpdate() {
        RuleName rule = new RuleName();
        RuleNameDTO dto = new RuleNameDTO();
        dto.setId(1);

        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(rule));

        ruleNameService.update(dto);

        verify(ruleNameMapper).updateRuleNameListFormDTO(dto, rule);
        verify(ruleNameRepository).save(rule);
    }

    @Test
    void testGetListRuleNameDTO() {
        RuleName r1 = new RuleName();
        RuleNameDTO dto1 = new RuleNameDTO();

        when(ruleNameMapper.toRuleNameDTO(r1)).thenReturn(dto1);

        List<RuleNameDTO> result = ruleNameService.getListRuleNameDTO(List.of(r1));

        assertEquals(1, result.size());
        verify(ruleNameMapper).toRuleNameDTO(r1);
    }

    @Test
    void testAdd() {
        RuleNameDTO dto = new RuleNameDTO();
        RuleName entity = new RuleName();

        when(ruleNameMapper.toRuleName(dto)).thenReturn(entity);
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(entity);

        ruleNameService.add(dto);

        verify(ruleNameMapper).toRuleName(dto);
        verify(ruleNameRepository).save(entity);
        assertNotNull(entity.getCreationDate());
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
}
