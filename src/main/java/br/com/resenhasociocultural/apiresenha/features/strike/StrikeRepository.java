package br.com.resenhasociocultural.apiresenha.features.strike;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StrikeRepository extends JpaRepository<StrikeEntry, Long> {
    List<StrikeEntry> findByYouthFirstNameContainingIgnoreCaseOrYouthSurnameContainingIgnoreCase(String firstName, String surname);
    List<StrikeEntry> findByMeetingDate(LocalDate date);
    List<StrikeEntry> findByMeetingDateBetween(LocalDate date1, LocalDate date2);
    List<StrikeEntry> findByMeetingDateGreaterThanEqual(LocalDate date);
}
