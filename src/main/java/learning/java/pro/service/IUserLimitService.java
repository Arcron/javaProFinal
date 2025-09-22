package learning.java.pro.service;

import learning.java.pro.dto.UserLimitRequestDto;

public interface IUserLimitService {

    void increaseDailyLimit(UserLimitRequestDto requestDto);

    void decreaseDailyLimit(UserLimitRequestDto requestDto);

    void resetUsersDailyLimit();
}
