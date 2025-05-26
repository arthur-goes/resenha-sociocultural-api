package br.com.resenhasociocultural.apiresenha.repository;

import br.com.resenhasociocultural.apiresenha.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    Optional<Meeting> findById(Long id);
    Optional<Meeting> findByDate(LocalDate date);
    List<Meeting> findByTheme(String theme);
}
