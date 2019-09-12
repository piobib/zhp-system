package pl.coderslab.zhpsystem.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Children {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @PESEL
    @Nullable
    private String pesel;

    private String phone;

    private String evidenceNumber;

    private Integer active;

    private LocalDateTime created;

    private String street;

    private String postCode;

    private String city;

    @OneToMany(mappedBy="children", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<ChildrenToken> tokens = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "childs")
    private Set<User> users = new HashSet<>();
}
