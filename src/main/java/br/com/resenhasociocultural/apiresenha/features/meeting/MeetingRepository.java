package br.com.resenhasociocultural.apiresenha.features.meeting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long>, JpaSpecificationExecutor<Meeting> {
    Optional<Meeting> findById(Long id);
    Optional<Meeting> findByDate(LocalDate date);
    List<Meeting> findByTheme(String theme);
}
