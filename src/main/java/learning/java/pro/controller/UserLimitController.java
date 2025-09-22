package learning.java.pro.controller;

import learning.java.pro.dto.UserLimitRequestDto;
import learning.java.pro.service.IUserLimitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/user-limits/v1")
public class UserLimitController {

    private final IUserLimitService userLimitService;

    @PostMapping(value = "/limit/daily/increase")
    public void increaseDailyLimit(@RequestBody UserLimitRequestDto requestDto) {
        log.info("Received request for increase limit for user {} for amount {}", requestDto.userId(), requestDto.amount());
        userLimitService.increaseDailyLimit(requestDto);
    }

    @PostMapping(value = "/limit/daily/decrease")
    public void decreaseDailyLimit(@RequestBody UserLimitRequestDto requestDto) {
        log.info("Received request for decrease limit for user {} for amount {}", requestDto.userId(), requestDto.amount());
        userLimitService.decreaseDailyLimit(requestDto);
    }
}
