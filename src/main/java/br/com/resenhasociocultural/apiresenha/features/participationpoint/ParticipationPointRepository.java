package br.com.resenhasociocultural.apiresenha.features.participationpoint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParticipationPointRepository extends JpaRepository<ParticipationPoint, Long>, JpaSpecificationExecutor<ParticipationPoint> {
    @Query("SELECT p FROM ParticipationPoint p LEFT JOIN FETCH p.youth WHERE p.id = :id")
    Optional<ParticipationPoint> findById(@Param("id") Long id);
}
