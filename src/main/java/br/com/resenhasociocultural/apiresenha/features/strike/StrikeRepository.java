package br.com.resenhasociocultural.apiresenha.features.strike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StrikeRepository extends JpaRepository<Strike, Long> {
    @Query("SELECT s FROM StrikeEntry s LEFT JOIN FETCH s.youth WHERE s.id = :id")
    Optional<Strike> findById();
}
