package learning.java.pro.controller;

import learning.java.pro.service.IMaxLimitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/max-limits/v1")
public class MaxLimitController {

    private final IMaxLimitService maxLimitService;

    @GetMapping(value = "/limit/max/{limit}")
    public void setMaxUserLimit(@PathVariable(value = "limit") Double limit) {
        log.info("Received change max users limit for new value: {}", limit);
        maxLimitService.updateCurrentMaxLimit(limit);
    }
}
