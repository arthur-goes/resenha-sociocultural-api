package br.com.resenhasociocultural.apiresenha.features.attendance;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {

    @Query("SELECT a FROM Attendance a LEFT JOIN FETCH a.youth WHERE a.id = :id")
    Optional<Attendance> findById(@Param("id") Long id);
}
