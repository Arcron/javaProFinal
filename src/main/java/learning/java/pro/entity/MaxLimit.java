package learning.java.pro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "max_limit_dictionary")
@Setter
@Getter
public class MaxLimit {

    @Id
    private Long id;
    private Double maxLimit;
}
