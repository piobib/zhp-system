package pl.coderslab.zhpsystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class ChildrenToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String token;

    private LocalDateTime created;

    private Integer active;

    @PrePersist  //oznaczenie metody, która zostanie wykonana przez operacją Persist, czyli zapisem do bazy
    public void prePersist() {
        created = LocalDateTime.now();
    }

    @ManyToOne
    //@JoinColumn(name = "children_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Children children;


}
