package pl.coderslab.zhpsystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.zhpsystem.validation.UserEditGroup;
import pl.coderslab.zhpsystem.validation.UserRegistrationGroup;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstName;


    private String lastName;

    @Email(groups={UserRegistrationGroup.class, UserEditGroup.class})
    @NotEmpty(groups={UserRegistrationGroup.class, UserEditGroup.class})
    private String username;

    private LocalDateTime created;

    private Integer enabled;

    @NotEmpty(groups=UserRegistrationGroup.class)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @PrePersist  //oznaczenie metody, która zostanie wykonana przez operacją Persist, czyli zapisem do bazy
    public void prePersist() {
        created = LocalDateTime.now();
    }

}
