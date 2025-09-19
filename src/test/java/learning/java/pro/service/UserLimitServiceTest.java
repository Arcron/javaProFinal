package learning.java.pro.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import learning.java.pro.dto.UserLimitRequestDto;
import learning.java.pro.entity.UserLimit;
import learning.java.pro.excetpion.LowDailyLimitException;
import learning.java.pro.repository.UserLimitRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserLimitServiceTest {

    @Mock
    private UserLimitRepository userLimitRepository;

    @Mock
    private IMaxLimitService maxLimitService;

    @InjectMocks
    private UserLimitService userLimitService;

    private static final Long userId = 1L;
    private static final Double amount = 500.0;
    private static final Double currentMaxLimit = 1000.0;

    private static UserLimit userLimit;
    private static UserLimit newUserLimit;

    @BeforeAll
    public static void prepareUserLimit() {
        userLimit = new UserLimit(userId, currentMaxLimit - amount);
        newUserLimit = new UserLimit(userId, currentMaxLimit);
    }

    @Test
    public void increaseDailyLimit_givenExistUserLimit_shouldIncreaseLimit() {
        //given
        when(userLimitRepository.findById(userId)).thenReturn(Optional.of(userLimit));
        when(maxLimitService.getCurrentMaxLimit()).thenReturn(currentMaxLimit);
        when(userLimitRepository.save(any())).thenReturn(newUserLimit);

        //when
        userLimitService.increaseDailyLimit(new UserLimitRequestDto(userId, amount));

        //then
        verify(userLimitRepository, times(1)).increaseDailyLimit(userId, amount);
    }

    @Test
    public void decreaseDailyLimit_givenExistUserLimit_shouldDecreaseLimit() {
        //given
        when(userLimitRepository.findById(userId)).thenReturn(Optional.of(userLimit));
        when(maxLimitService.getCurrentMaxLimit()).thenReturn(currentMaxLimit);
        when(userLimitRepository.save(any())).thenReturn(newUserLimit);

        //when
        userLimitService.decreaseDailyLimit(new UserLimitRequestDto(userId, amount));

        //then
        verify(userLimitRepository, times(1)).decreaseDailyLimit(userId, amount);
    }

    @Test
    public void decreaseDailyLimit_givenExistUserLimitAndLowLimit_shouldThrowLowDailyLimitException() {
        //given
        when(userLimitRepository.findById(userId)).thenReturn(Optional.of(userLimit));
        when(maxLimitService.getCurrentMaxLimit()).thenReturn(currentMaxLimit);
        when(userLimitRepository.save(any())).thenReturn(newUserLimit);

        //when
        assertThrows(LowDailyLimitException.class, () ->
                userLimitService.decreaseDailyLimit(new UserLimitRequestDto(userId, amount * 5))
        );

        //then
        verify(userLimitRepository, never()).decreaseDailyLimit(anyLong(), anyDouble());
    }

    @Test
    public void resetUsersDailyLimit_givenValid_shouldResetCurrentLimit() {
        Double currentMaxLimit = 1000.0;

        when(maxLimitService.getCurrentMaxLimit()).thenReturn(currentMaxLimit);

        userLimitService.resetUsersDailyLimit();

        verify(userLimitRepository, times(1)).resetUsersDailyLimit(currentMaxLimit);
    }
}
