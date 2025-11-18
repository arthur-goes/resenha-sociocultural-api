package br.com.resenhasociocultural.apiresenha.features.attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttendanceRepository extends JpaRepository<AttendanceEntry, Long>, JpaSpecificationExecutor<AttendanceEntry> {}
