package br.com.resenhasociocultural.apiresenha.features.youth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface YouthRepository extends JpaRepository<Youth, Long>, JpaSpecificationExecutor<Youth> {
    List<Youth> findByFirstName(String firstName);
    List<Youth> findByFirstNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String firstName, String surname);
    List<Youth> findByBirthDateGreaterThanEqual(LocalDate birthDate);
    List<Youth> findByActive(boolean active);
}
