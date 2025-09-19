package learning.java.pro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import learning.java.pro.entity.MaxLimit;
import learning.java.pro.repository.MaxLimitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MaxLimitServiceTest {

    @Mock
    private MaxLimitRepository maxLimitRepository;

    @Mock
    private CacheService cacheService;

    @InjectMocks
    private MaxLimitService maxLimitService;

    @Test
    public void getCurrentMaxLimit_givenValid_shouldReturnCurrentMaxLimit() {
        //given
        Long id = 1L;
        Double expectedLimit = 1000.0;
        MaxLimit maxLimit = new MaxLimit();
        maxLimit.setMaxLimit(expectedLimit);

        when(maxLimitRepository.findById(id)).thenReturn(Optional.of(maxLimit));

        //when
        Double actualLimit = maxLimitService.getCurrentMaxLimit();

        //then
        assertEquals(expectedLimit, actualLimit);
        verify(maxLimitRepository, times(1)).findById(id);
    }

    @Test
    public void updateCurrentMaxLimit_givenNewLimit_shouldUpdateLimit() {
        //given
        Double newLimit = 1500.0;

        //when
        maxLimitService.updateCurrentMaxLimit(newLimit);

        //then
        verify(maxLimitRepository, times(1)).updateCurrentLimit(newLimit);
    }
}
