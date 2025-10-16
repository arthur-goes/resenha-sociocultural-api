package br.com.resenhasociocultural.apiresenha.features.role;

import br.com.resenhasociocultural.apiresenha.features.userprofile.UserProfile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
public class Role {
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<UserProfile> users;

    public String getAuthority(){
        return "ROLE_" + this.name;
    }
}
