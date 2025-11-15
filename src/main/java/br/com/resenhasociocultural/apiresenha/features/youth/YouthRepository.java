package br.com.resenhasociocultural.apiresenha.features.youth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface YouthRepository extends JpaRepository<Youth, Long>, JpaSpecificationExecutor<Youth> {
    List<Youth> findByActive(boolean active);
    Set<Long> findIdsByIdIn(Set<Long> ids);
}
