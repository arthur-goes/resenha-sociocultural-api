package br.com.resenhasociocultural.apiresenha.features.strike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StrikeRepository extends JpaRepository<Strike, Long>, JpaSpecificationExecutor<Strike> {
    @Query("SELECT s FROM Strike s LEFT JOIN FETCH s.youth WHERE s.id = :id")
    Optional<Strike> findById(@Param("id") Long id);
}
