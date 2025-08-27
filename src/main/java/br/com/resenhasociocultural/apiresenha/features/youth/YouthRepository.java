package br.com.resenhasociocultural.apiresenha.features.youth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface YouthRepository extends JpaRepository<Youth, Long> {
    List<Youth> findByFirstName(String firstName);
    List<Youth> findByFirstNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String firstName, String surname);
    List<Youth> findByBirthDateGreaterThanEqual(LocalDate birthDate);
}
