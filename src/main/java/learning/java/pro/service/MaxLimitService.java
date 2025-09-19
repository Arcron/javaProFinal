package learning.java.pro.service;

import learning.java.pro.entity.MaxLimit;
import learning.java.pro.repository.MaxLimitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaxLimitService implements IMaxLimitService {

    final private MaxLimitRepository maxLimitRepository;
    final private CacheService cacheService;

    @Override
    @Transactional
    @Cacheable("maxLimitCache")
    public Double getCurrentMaxLimit() {
        Optional<MaxLimit> maxLimitOptional = maxLimitRepository.findById(1L);
        MaxLimit maxLimit = maxLimitOptional.orElseThrow();
        return maxLimit.getMaxLimit();
    }

    @Override
    public void updateCurrentMaxLimit(Double newLimit) {
        log.info("Try to update max limit. New value: {}", newLimit);
        maxLimitRepository.updateCurrentLimit(newLimit);
        cacheService.clearCache();
        log.info("Successfully update max limit. New value: {}", newLimit);
    }
}
