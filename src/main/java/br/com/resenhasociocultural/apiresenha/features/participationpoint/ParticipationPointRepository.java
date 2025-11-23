package br.com.resenhasociocultural.apiresenha.features.participationpoint;

import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParticipationPointRepository extends JpaRepository<ParticipationPointEntry, Long> {
    @Query("SELECT p FROM ParticipationPointEntry p LEFT JOIN FETCH p.youth WHERE p.id = :id")
    Optional<ParticipationPointEntry> findById();
}
