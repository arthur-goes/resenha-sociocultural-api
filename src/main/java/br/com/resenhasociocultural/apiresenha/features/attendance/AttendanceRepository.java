package br.com.resenhasociocultural.apiresenha.features.attendance;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {
    Set<Attendance> findByYouthFirstNameContainingIgnoreCaseOrYouthSurnameContainingIgnoreCase(String firstName, String surname);
    Set<Attendance> findByMeetingId(Long id);
    Set<Attendance> findByMeetingDate(LocalDate date);
    Set<Attendance> findByMeetingDateBetween(LocalDate initialDate, LocalDate finalDate);

    default Set<Attendance> findAllAsSet(){
        return new HashSet<>(findAll());
    }

    default Set<Attendance> findAllAsSet(Specification<Attendance> specs){
        return new HashSet<>(findAll(specs));
    }
}
