package br.com.resenhasociocultural.apiresenha.features.userprofile;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>, JpaSpecificationExecutor<UserProfile> {
    @Query("select u FROM UserProfile u JOIN FETCH u.roles WHERE u.username = :username")
    Optional<UserProfile> findByUsername(@Param("username") String username);
}
