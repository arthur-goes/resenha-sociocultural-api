package br.com.resenhasociocultural.apiresenha.features.attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<AttendanceEntry, Long>, JpaSpecificationExecutor<AttendanceEntry> {

    @Query("SELECT a FROM AttendanceEntry a LEFT JOIN FETCH a.youth WHERE a.id = :id")
    Optional<AttendanceEntry> findById();
}
