package br.com.resenhasociocultural.apiresenha.config;

import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.features.meeting.MeetingRepository;
import br.com.resenhasociocultural.apiresenha.features.role.Role;
import br.com.resenhasociocultural.apiresenha.features.role.RoleRepository;
import br.com.resenhasociocultural.apiresenha.features.userprofile.UserProfile;
import br.com.resenhasociocultural.apiresenha.features.userprofile.UserProfileRepository;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashSet;

@Configuration
@Profile({"dev", "staging"})
@AllArgsConstructor
@Slf4j
public class DataSeedingConfig {

    private UserProfileRepository userProfileRepository;
    private RoleRepository roleRepository;

    private ResourceLoader resourceLoader;
    private DataSource dataSource;

    private YouthRepository youthRepository;
    private MeetingRepository meetingRepository;


    @Value("${app.database.seed-on-startup:false}")
    private boolean seedExampleData;

    @Bean
    @Order(1)
    CommandLineRunner addDefaultRoles(){
        return args -> {
            createRoleIfNotFound("ADMIN");
            createRoleIfNotFound("COORDINATOR");
            createRoleIfNotFound("USER");
        };
    }

    @Bean
    @Order(2)
    CommandLineRunner addDefaultAdminUser(PasswordEncoder encoder){
        return args -> {
            boolean doesNotHaveAnUserPersisted = userProfileRepository.count() == 0;

            if (doesNotHaveAnUserPersisted){
                var user = createUserAdmin(encoder);
                userProfileRepository.save(user);
                log.info("Default admin user created due to the absence of registered users.");
            }
        };
    }

    @Bean
    CommandLineRunner addExampleData(){
        return args -> {
            boolean youthTabelIsNotEmpty = youthRepository.count() > 0;
            boolean meetingTableIsNotEmpty = meetingRepository.count() > 0;

            if (youthTabelIsNotEmpty && meetingTableIsNotEmpty){ return; }

            if (seedExampleData) {
                try (var connection = dataSource.getConnection()) {
                    var resource = resourceLoader.getResource("classpath:dev-example-data.sql");
                    ScriptUtils.executeSqlScript(connection, resource);
                }
            }
        };
    }

    private void createRoleIfNotFound(String roleName) {
        if (roleRepository.findByName(roleName).isEmpty()) {
            roleRepository.save(new Role(null, roleName, new HashSet<>()));
            log.info("Role criada: " + roleName);
        }
    }

    private UserProfile createUserAdmin(PasswordEncoder encoder){
        var roleAdmin = roleRepository.findByName("ADMIN")
            .orElseThrow(() -> new ResourceNotFoundException("Could not find ADMIN role."));

        var user = new UserProfile(
            null,
            "admin",
            encoder.encode("admin"),
            "admin",
            "System",
            LocalDate.now(),
            new HashSet<>()
        );

        user.getRoles().add(roleAdmin);

        return user;
    }
}
