package br.com.resenhasociocultural.apiresenha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    @Profile("dev")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> {
                csrf.disable();

            })
            .headers(headers ->
                headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
            )
            .httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests(authorization -> {
                authorization.requestMatchers(HttpMethod.POST, "/user").anonymous();
                authorization.anyRequest().authenticated();
            });

        return http.build();
    }

    @Bean
    @Profile("dev")
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
        String hierarchyString = "ROLE_ADMIN > ROLE_DIRECTOR > ROLE_COORDINATOR > ROLE_USER";
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
