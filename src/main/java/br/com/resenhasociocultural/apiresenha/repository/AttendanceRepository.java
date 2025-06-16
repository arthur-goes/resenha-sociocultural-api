package br.com.resenhasociocultural.apiresenha.repository;

import br.com.resenhasociocultural.apiresenha.model.Attendance;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {
    List<Attendance> findByYouthFirstNameContainingIgnoreCaseOrYouthSurnameContainingIgnoreCase(String firstName, String surname);
    List<Attendance> findByMeetingId(Long id);
    List<Attendance> findByMeetingDate(LocalDate date);
    List<Attendance> findByMeetingDateBetween(LocalDate initialDate, LocalDate finalDate);
}
