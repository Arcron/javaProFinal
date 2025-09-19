package learning.java.pro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_limit")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLimit {

    @Id
    private Long userId;
    private Double dailyLimit;
}
