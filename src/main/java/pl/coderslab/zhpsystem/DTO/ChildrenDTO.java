package pl.coderslab.zhpsystem.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

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

}
