package pl.coderslab.zhpsystem.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.pl.PESEL;
import pl.coderslab.zhpsystem.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(mappedBy = "childrens")
    private Set<User> users = new HashSet<>();
}
