package br.com.resenhasociocultural.apiresenha.features.userprofile;

import br.com.resenhasociocultural.apiresenha.features.role.Role;
import br.com.resenhasociocultural.apiresenha.shared.domain.Person;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "{id}")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class UserProfile implements Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, name = "creation_date")
    @CreatedDate
    private LocalDate creationDate;

    @ManyToMany
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
