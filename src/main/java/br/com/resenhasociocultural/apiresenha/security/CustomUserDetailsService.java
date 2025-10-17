package br.com.resenhasociocultural.apiresenha.security;

import br.com.resenhasociocultural.apiresenha.features.role.Role;
import br.com.resenhasociocultural.apiresenha.features.userprofile.UserProfile;
import br.com.resenhasociocultural.apiresenha.features.userprofile.UserProfileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private UserProfileService userProfileService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserProfile> foundUserProfile = userProfileService.findByUsername(username);
        if (foundUserProfile.isEmpty()){
            throw new UsernameNotFoundException("Não foi possível encontrar um cadastro para o usuário " + username);
        }

        UserProfile userProfile = foundUserProfile.get();
        String[] roles = toAuthoritiesArray(userProfile.getRoles());
        Arrays.stream(roles).forEach(System.out::println);

        return User
            .withUsername(userProfile.getUsername())
            .password(userProfile.getPassword())
            .authorities(roles)
            .build();
    }

    private String[] toAuthoritiesArray(Set<Role> roles){
        return roles.stream()
            .map(Role::getAuthority)
            .toArray(String[]::new);
    }
}
