package learning.java.pro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users_limit")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLimit {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "daily_limit")
    private Double dailyLimit;
}
