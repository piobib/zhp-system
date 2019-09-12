package pl.coderslab.zhpsystem.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.zhpsystem.entity.Children;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ChildrenTokenDTO {

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
    private ChildrenDTO childrenDTO;

}
