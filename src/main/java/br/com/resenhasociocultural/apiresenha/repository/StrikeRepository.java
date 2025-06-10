package br.com.resenhasociocultural.apiresenha.repository;

import br.com.resenhasociocultural.apiresenha.model.Strike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StrikeRepository extends JpaRepository<Strike, Long> {
    List<Strike> findByYouthFirstNameContainingIgnoreCaseOrYouthSurnameContainingIgnoreCase(String firstName, String surname);
    List<Strike> findByMeetingDate(LocalDate date);
    List<Strike> findByMeetingDateBetween(LocalDate date1, LocalDate date2);
    List<Strike> findByMeetingDateGreaterThanEqual(LocalDate date);
}
