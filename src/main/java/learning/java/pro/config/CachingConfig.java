package learning.java.pro.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("maxLimitCache");
    }

}
