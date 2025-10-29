package br.com.resenhasociocultural.apiresenha.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults())
            .oauth2ResourceServer(oauth2rs -> oauth2rs.jwt(Customizer.withDefaults()))
            .authorizeHttpRequests(authorization -> {
                authorization
                    .requestMatchers(HttpMethod.POST, "/user").anonymous()
                    .requestMatchers("/login").permitAll()
                    .anyRequest().authenticated();
            });

        return http.build();
    }

    //@Bean
    //@Profile("dev")
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails admin = User.builder()
            .password(encoder.encode("admin"))
            .username("admin")
            .authorities("ROLE_ADMIN")
            .build();

        UserDetails director = User.builder()
            .password(encoder.encode("director"))
            .username("director")
            .authorities("ROLE_DIRECTOR")
            .build();

        UserDetails coordinator = User.builder()
            .password(encoder.encode("coordinator"))
            .username("coordinator")
            .authorities("ROLE_COORDINATOR")
            .build();

        UserDetails user = User.builder()
            .password(encoder.encode("user"))
            .username("user")
            .authorities("ROLE_USER")
            .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public RoleHierarchy roleHierarchy(){
        String hierarchyString = "ROLE_ADMIN > ROLE_MANAGER > ROLE_COORDINATOR > ROLE_USER";
        RoleHierarchyImpl hierarchy = RoleHierarchyImpl.fromHierarchy(hierarchyString);
        return hierarchy;
    }

    @Bean
    public MethodSecurityExpressionHandler expressionHandler(RoleHierarchy hierarchy){
        DefaultMethodSecurityExpressionHandler defaultExpressionHandler = new DefaultMethodSecurityExpressionHandler();
        defaultExpressionHandler.setRoleHierarchy(hierarchy);
        return defaultExpressionHandler;
    }
}
