package br.com.resenhasociocultural.apiresenha.features.participationpoint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParticipationPointRepository extends JpaRepository<ParticipationPoint, Long> {
    @Query("SELECT p FROM ParticipationPointEntry p LEFT JOIN FETCH p.youth WHERE p.id = :id")
    Optional<ParticipationPoint> findById();
}
