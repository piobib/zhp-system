package pl.coderslab.zhpsystem.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import pl.coderslab.zhpsystem.entity.Role;
import pl.coderslab.zhpsystem.validation.UserEditGroup;
import pl.coderslab.zhpsystem.validation.UserRegistrationGroup;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@ToString
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Email(groups={UserRegistrationGroup.class, UserEditGroup.class})
    @NotEmpty(groups={UserRegistrationGroup.class, UserEditGroup.class})
    private String username;

    @NotEmpty(groups=UserRegistrationGroup.class)
    private String password;

    @NotEmpty(groups=UserRegistrationGroup.class)
    private String confirmPassword;

    private LocalDateTime created;

    private Integer enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
