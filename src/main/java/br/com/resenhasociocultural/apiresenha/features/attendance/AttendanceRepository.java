package br.com.resenhasociocultural.apiresenha.features.attendance;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public interface AttendanceRepository extends JpaRepository<AttendanceEntry, Long>, JpaSpecificationExecutor<AttendanceEntry> {
    Set<AttendanceEntry> findByYouthFirstNameContainingIgnoreCaseOrYouthSurnameContainingIgnoreCase(String firstName, String surname);
    Set<AttendanceEntry> findByMeetingId(Long id);
    Set<AttendanceEntry> findByMeetingDate(LocalDate date);
    Set<AttendanceEntry> findByMeetingDateBetween(LocalDate initialDate, LocalDate finalDate);

    default Set<AttendanceEntry> findAllAsSet(){
        return new HashSet<>(findAll());
    }

    default Set<AttendanceEntry> findAllAsSet(Specification<AttendanceEntry> specs){
        return new HashSet<>(findAll(specs));
    }
}
