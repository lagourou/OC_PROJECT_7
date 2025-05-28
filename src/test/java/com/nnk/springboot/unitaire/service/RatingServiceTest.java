package com.nnk.springboot.unitaire.service;

import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.mapper.RatingMapper;
import com.nnk.springboot.model.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private RatingMapper ratingMapper;

    @InjectMocks
    private RatingService ratingService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRating() {
        Rating rating = new Rating();
        when(ratingRepository.findAll()).thenReturn(List.of(rating));

        List<Rating> result = ratingService.getAllRating();

        assertEquals(1, result.size());
        verify(ratingRepository).findAll();
    }

    @Test
    void testGetAllRatingListDTO() {
        Rating rating = new Rating();
        RatingDTO dto = new RatingDTO();

        when(ratingRepository.findAll()).thenReturn(List.of(rating));
        when(ratingMapper.toRatingDTO(rating)).thenReturn(dto);

        List<RatingDTO> result = ratingService.getAllRatingListDTO();

        assertEquals(1, result.size());
        verify(ratingMapper).toRatingDTO(rating);
    }

    @Test
    void testGetRatingIdFound() {
        Rating rating = new Rating();
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        Rating result = ratingService.getRatingId(1);

        assertNotNull(result);
        verify(ratingRepository).findById(1);
    }

    @Test
    void testGetRatingIdNotFound() {
        when(ratingRepository.findById(1)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> ratingService.getRatingId(1));
        assertEquals("Rating with ID 1 not found", ex.getMessage());
    }

    @Test
    void testSave() {
        Rating rating = new Rating();
        when(ratingRepository.save(rating)).thenReturn(rating);

        Rating result = ratingService.save(rating);

        assertEquals(rating, result);
        verify(ratingRepository).save(rating);
    }

    @Test
    void testDelete() {
        Rating rating = new Rating();

        ratingService.delete(rating);

        verify(ratingRepository).delete(rating);
    }

    @Test
    void testUpdate() {
        Rating rating = new Rating();
        RatingDTO dto = new RatingDTO();
        dto.setId(1);

        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        ratingService.update(dto);

        verify(ratingMapper).updateRatingListFromDTO(dto, rating);
        verify(ratingRepository).save(rating);
    }

    @Test
    void testGetListRatingDTO() {
        Rating r1 = new Rating();
        RatingDTO dto1 = new RatingDTO();

        when(ratingMapper.toRatingDTO(r1)).thenReturn(dto1);

        List<RatingDTO> result = ratingService.getListRatingDTO(List.of(r1));

        assertEquals(1, result.size());
        verify(ratingMapper).toRatingDTO(r1);
    }

    @Test
    void testAdd() {
        RatingDTO dto = new RatingDTO();
        Rating entity = new Rating();

        when(ratingMapper.toRating(dto)).thenReturn(entity);
        when(ratingRepository.save(any(Rating.class))).thenReturn(entity);

        ratingService.add(dto);

        verify(ratingMapper).toRating(dto);
        verify(ratingRepository).save(entity);
        assertNotNull(entity.getCreationDate());
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
}
