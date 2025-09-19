package learning.java.pro.service;

import learning.java.pro.dto.UserLimitRequestDto;
import learning.java.pro.entity.UserLimit;
import learning.java.pro.excetpion.LowDailyLimitException;
import learning.java.pro.repository.UserLimitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLimitService implements IUserLimitService {

    private final UserLimitRepository userLimitRepository;
    private final IMaxLimitService maxLimitService;

    @Override
    public void increaseDailyLimit(UserLimitRequestDto requestDto) {
        log.info("Try to increase daily limit for user with id: {}, by amount: {}", requestDto.userId(), requestDto.amount());
        Optional<UserLimit> userLimitOptional = userLimitRepository.findById(requestDto.userId());
        UserLimit userLimit = userLimitOptional.orElse(createNewUserLimit(requestDto.userId()));
        if (requestDto.amount() + userLimit.getDailyLimit() > maxLimitService.getCurrentMaxLimit()) {
            userLimitRepository.resetUserDailyLimit(userLimit.getUserId(), maxLimitService.getCurrentMaxLimit());
        } else {
            userLimitRepository.increaseDailyLimit(userLimit.getUserId(), requestDto.amount());
        }
        log.info("Daily limit increased for user with id: {}, by amount: {}", requestDto.userId(), requestDto.amount());

    }

    @Override
    public void decreaseDailyLimit(UserLimitRequestDto requestDto) {
        log.info("Try to decrease daily limit for user with id: {}, by amount: {}", requestDto.userId(), requestDto.amount());
        Optional<UserLimit> userLimitOptional = userLimitRepository.findById(requestDto.userId());
        UserLimit userLimit = userLimitOptional.orElse(createNewUserLimit(requestDto.userId()));
        if (requestDto.amount() > userLimit.getDailyLimit()) {
            throw new LowDailyLimitException("Attempt to decrease daily limit below zero. " +
                    "Current limit: " + userLimit.getDailyLimit() +
                    ", requested decrease: " + requestDto.amount() + ".");
        }
        userLimitRepository.decreaseDailyLimit(requestDto.userId(), requestDto.amount());
        log.info("Daily limit decreased for user with id: {}, by amount: {}", requestDto.userId(), requestDto.amount());
    }

    @Override
    public void resetUsersDailyLimit() {
        log.info("Try to reset users daily limit");
        userLimitRepository.resetUsersDailyLimit(maxLimitService.getCurrentMaxLimit());
        log.info("Users daily limit reset successfully");
    }

    private UserLimit createNewUserLimit(Long userId) {
        log.info("User with id: {} does not exist", userId);
        UserLimit newUserLimit = new UserLimit(userId, maxLimitService.getCurrentMaxLimit());
        UserLimit userLimit = userLimitRepository.save(newUserLimit);
        log.info("Create new user with id: {}", userLimit.getUserId());
        return userLimit;
    }

    @Scheduled(cron = "${scheduler.limit.daily.update}")
    void updateDailyLimits() {
        log.info("Updating daily limits for all users");
        userLimitRepository.resetUsersDailyLimit(maxLimitService.getCurrentMaxLimit());
        log.info("Daily limits updated successfully");
    }
}
