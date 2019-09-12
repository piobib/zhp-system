package pl.coderslab.zhpsystem.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.lang.Nullable;
import pl.coderslab.zhpsystem.entity.ChildrenToken;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ChildrenDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @PESEL
    private String pesel;

    private String phone;

    private String evidenceNumber;

    private Integer active;

    private LocalDateTime created;

    private String street;

    private String postCode;

    private String city;

    @OneToMany(mappedBy="children", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<ChildrenTokenDTO> tokens = new ArrayList<>();

}
