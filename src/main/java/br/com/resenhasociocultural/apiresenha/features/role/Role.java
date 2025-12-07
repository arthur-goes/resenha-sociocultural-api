package br.com.resenhasociocultural.apiresenha.features.role;

import br.com.resenhasociocultural.apiresenha.features.userprofile.UserProfile;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<UserProfile> users;

    public String getAuthority(){
        return "ROLE_" + this.name;
    }

    public void addUserProfile(UserProfile user){
        users.add(user);
    }
}
