package learning.java.pro.repository;

import learning.java.pro.entity.UserLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserLimitRepository extends JpaRepository<UserLimit, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserLimit u SET u.daily_limit = u.daily_limit - :amount WHERE u.user_id = :id", nativeQuery = true)
    void decreaseDailyLimit(Long id, Double amount);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserLimit u SET u.daily_limit = u.daily_limit + :amount WHERE u.user_id = :id", nativeQuery = true)
    void increaseDailyLimit(Long id, Double amount);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserLimit u SET u.daily_limit = :newLimit", nativeQuery = true)
    void resetUsersDailyLimit(Double newLimit);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserLimit u SET u.daily_limit = :newLimit WHERE u.user_id = :id", nativeQuery = true)
    void resetUserDailyLimit(Long userId, Double newLimit);
}
