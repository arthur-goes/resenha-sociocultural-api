package br.com.resenhasociocultural.apiresenha.repository;

import br.com.resenhasociocultural.apiresenha.model.Strike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StrikeRepository extends JpaRepository<Strike, Long> {
    public List<Strike> findByYouthNameContainingIgnoreCaseOrYouthSurnameContainingIgnoreCase(String firstName, String surname);
    public List<Strike> findByMeetingDate(LocalDate date);
    public List<Strike> findByMeetingDateBetween(LocalDate date);
    public List<Strike> findByMeetingDateGreaterThanEqual(LocalDate date);
}
