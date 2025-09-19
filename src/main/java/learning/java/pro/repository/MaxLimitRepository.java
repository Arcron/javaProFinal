package learning.java.pro.repository;

import learning.java.pro.entity.MaxLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MaxLimitRepository extends JpaRepository<MaxLimit, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE max_limit_dictionary SET max_limit = :newLimit WHERE id = 1", nativeQuery = true)
    void updateCurrentLimit(Double newLimit);
}
