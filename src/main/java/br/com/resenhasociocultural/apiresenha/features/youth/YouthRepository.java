package br.com.resenhasociocultural.apiresenha.features.youth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface YouthRepository extends JpaRepository<Youth, Long>, JpaSpecificationExecutor<Youth> {
    List<Youth> findByActive(boolean active);

    @Query("SELECT y.id FROM Youth y WHERE y.id IN :ids")
    Set<Long> findIdsByIdIn(@Param("ids") Set<Long> ids);
}
