package br.com.resenhasociocultural.apiresenha.security;

import br.com.resenhasociocultural.apiresenha.features.role.Role;
import br.com.resenhasociocultural.apiresenha.features.userprofile.UserProfile;
import br.com.resenhasociocultural.apiresenha.features.userprofile.UserProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

import static br.com.resenhasociocultural.apiresenha.features.role.builder.RoleBuilder.aRole;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static br.com.resenhasociocultural.apiresenha.features.userprofile.builder.UserProfileBuilder.anUserProfile;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsTest {
    @Mock
    private UserProfileService userProfileService;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    private UserProfile user;

    @Test
    public void givenValidUsername_whenLoadingUserByUsername_shouldReturnUserDetails(){

        when(userProfileService.findByUsername(any(String.class))).thenReturn(Optional.of(this.user));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        authorities.forEach((authority) -> {
            assertThat(
                authority.getAuthority().equals("ROLE_ADMIN") || authority.getAuthority().equals("ROLE_USER")
            ).isTrue();
        });

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(user.getUsername());
        assertThat(userDetails.getPassword()).isEqualTo(user.getPassword());
        assertThat(userDetails.getAuthorities().size()).isEqualTo(2);
    }

    @Test
    public void givenInvalidUsername_whenLoadingUserByUsername_shouldThrowException(){
        when(userProfileService.findByUsername(any())).thenReturn(Optional.empty());

        assertThatExceptionOfType(UsernameNotFoundException.class).isThrownBy(()->{
            customUserDetailsService.loadUserByUsername("username");
        });
    }

    @BeforeEach
    public void setUp(){
        UserProfile user = anUserProfile().build();
        Role roleAdmin = aRole().asAdmin();
        Role roleUser = aRole().asUser();

        user.addRole(roleAdmin);
        user.addRole(roleUser);

        this.user = user;
    }
}
